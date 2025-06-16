package utilitaire;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class BdConnect {

    private Connection connexion;

    // Constructeur
    public BdConnect() throws SQLException {
        try {
            // Charger le pilote MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Utiliser DBUtil pour obtenir les détails de la connexion
            DBUtil dbUtil = new DBUtil();
            this.connexion = DriverManager.getConnection(dbUtil.getUrl(), dbUtil.getUser(), dbUtil.getPassword());
            System.out.println("Connexion réussie à la base de données.");
        } catch (ClassNotFoundException e) {
            System.out.println("Erreur : le pilote JDBC MySQL est introuvable.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Erreur de connexion à la base de données : " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Méthode généralisée pour récupérer des objets à partir de n'importe quelle table
    public <T> T[] getObjectFromTable(String tableName, Class<T> clazz) {
    List<T> results = new ArrayList<>();
    try {
        String query = "SELECT * FROM " + tableName;
        PreparedStatement preparedStatement = connexion.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();

        // Obtenir les champs de la classe
        Field[] fields = clazz.getDeclaredFields();

        // Itérer sur les lignes du résultat
        while (resultSet.next()) {
            Constructor<T> constructor = clazz.getDeclaredConstructor();
            T instance = constructor.newInstance();

            // Assigner les valeurs du résultat aux champs de l'instance
            for (Field field : fields) {
                field.setAccessible(true);
                String fieldName = field.getName();
                Object value = resultSet.getObject(fieldName);

                // Convertir BigDecimal en double si le champ cible est de type double
                if (value instanceof BigDecimal && field.getType().equals(double.class)) {
                    value = ((BigDecimal) value).doubleValue();
                }

                // Assigner la valeur convertie (ou non convertie) au champ
                field.set(instance, value);
            }

            results.add(instance);
        }

    } catch (SQLException e) {
        System.out.println("Erreur lors de l'exécution de la requête SQL : " + e.getMessage());
        e.printStackTrace();
    } catch (Exception e) {
        e.printStackTrace();
    }
    
    @SuppressWarnings("unchecked")
    T[] array = (T[]) java.lang.reflect.Array.newInstance(clazz, results.size());
    return results.toArray(array);
}

    

    public <T> void insertObjectIntoTable(String tableName, T object) {
        try {
            // Obtenir les champs de l'objet
            Field[] fields = object.getClass().getDeclaredFields();

            // Construire la partie des colonnes pour la requête SQL
            StringJoiner columns = new StringJoiner(", ");
            StringJoiner values = new StringJoiner(", ");
            for (Field field : fields) {
                field.setAccessible(true);  // Rendre le champ accessible
                columns.add(field.getName());  // Ajouter le nom du champ
                values.add("?");  // Placeholder pour la valeur
            }

            // Construire la requête SQL
            String sql = "INSERT INTO " + tableName + " (" + columns.toString() + ") VALUES (" + values.toString() + ")";
            PreparedStatement preparedStatement = connexion.prepareStatement(sql);

            // Assigner les valeurs des champs aux placeholders
            int index = 1;
            for (Field field : fields) {
                Object value = field.get(object);  // Récupérer la valeur du champ
                preparedStatement.setObject(index++, value);  // Assigner la valeur au placeholder
            }

            // Exécuter l'insertion
            preparedStatement.executeUpdate();
            System.out.println("Insertion réussie dans la table " + tableName);
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'insertion dans la base de données : " + e.getMessage());
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            System.out.println("Erreur lors de l'accès aux champs de l'objet : " + e.getMessage());
            e.printStackTrace();
        }
    }

    public <T> void updateObjectInTable(String tableName, T object, String primaryKeyField) {
        try {
            Field[] fields = object.getClass().getDeclaredFields();
            StringBuilder query = new StringBuilder("UPDATE " + tableName + " SET ");
            String primaryKeyValue = null;
            List<Object> nonNullValues = new ArrayList<>();  // Liste des valeurs non nulles
    
            // Construire la partie SET de la requête
            for (Field field : fields) {
                field.setAccessible(true);  // Accéder aux champs privés
                Object fieldValue = field.get(object);  // Obtenir la valeur du champ
    
                // Vérifier si c'est la clé primaire
                if (field.getName().equals(primaryKeyField)) {
                    primaryKeyValue = fieldValue != null ? fieldValue.toString() : null;
                    continue;
                }
    
                // Inclure uniquement les champs dont la valeur n'est pas nulle
                // ou qui ne sont pas des valeurs par défaut (comme 0 pour les int)
                if (fieldValue != null && !isDefaultValue(field, fieldValue)) {
                    query.append(field.getName()).append(" = ?, ");
                    nonNullValues.add(fieldValue);  // Ajouter la valeur à la liste
                }
            }
    
            // Si aucune valeur non nulle, annuler la mise à jour
            if (nonNullValues.isEmpty()) {
                System.out.println("Aucune valeur non nulle à mettre à jour.");
                return;
            }
    
            // Retirer la dernière virgule et espace
            query.setLength(query.length() - 2);
            query.append(" WHERE ").append(primaryKeyField).append(" = ?");
    
            // Créer la requête préparée
            PreparedStatement preparedStatement = connexion.prepareStatement(query.toString());
    
            // Remplir les paramètres avec les valeurs non nulles
            int paramIndex = 1;
            for (Object value : nonNullValues) {
                preparedStatement.setObject(paramIndex++, value);
            }
    
            // Définir la clé primaire en dernier
            if (primaryKeyValue != null) {
                preparedStatement.setObject(paramIndex, primaryKeyValue);
            } else {
                System.out.println("Erreur : la clé primaire ne peut pas être nulle.");
                return;
            }
    
            // Exécuter la mise à jour
            int affectedRows = preparedStatement.executeUpdate();
            System.out.println("Mise à jour réussie, lignes affectées : " + affectedRows);
    
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'exécution de la requête SQL : " + e.getMessage());
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            System.out.println("Erreur d'accès aux champs de l'objet : " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    // Méthode pour vérifier si une valeur est la valeur par défaut pour le type de champ
    private boolean isDefaultValue(Field field, Object value) {
        Class<?> type = field.getType();
        if (type.equals(int.class) || type.equals(Integer.class)) {
            return ((Integer) value) == 0;  // Vérifie si c'est 0
        } else if (type.equals(double.class) || type.equals(Double.class)) {
            return ((Double) value) == 0.0;  // Vérifie si c'est 0.0
        } else if (type.equals(float.class) || type.equals(Float.class)) {
            return ((Float) value) == 0.0f;  // Vérifie si c'est 0.0f
        } else if (type.equals(long.class) || type.equals(Long.class)) {
            return ((Long) value) == 0L;  // Vérifie si c'est 0L
        } else if (type.equals(short.class) || type.equals(Short.class)) {
            return ((Short) value) == 0;  // Vérifie si c'est 0
        } else if (type.equals(byte.class) || type.equals(Byte.class)) {
            return ((Byte) value) == 0;  // Vérifie si c'est 0
        } else if (type.equals(boolean.class) || type.equals(Boolean.class)) {
            return !((Boolean) value);  // Vérifie si c'est false
        }
        return false;  // Pour les autres types, retourne false
    }

    public int getLastInsertIdFromTable(String key,String tableName) throws SQLException {
        int lastInsertId = -1;
        try {
            // Créer une requête pour récupérer le dernier ID inséré dans la table spécifiée
            String sql = "SELECT MAX("+key+") as lastId FROM " + tableName;
            
            // Préparer la requête SQL
            PreparedStatement preparedStatement = connexion.prepareStatement(sql);
            
            // Exécuter la requête et récupérer les résultats
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                lastInsertId = resultSet.getInt("lastId");  // Récupérer l'ID inséré
            }
    
            System.out.println("Dernier ID inséré dans la table " + tableName + " : " + lastInsertId);
    
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération du dernier ID inséré : " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    
        return lastInsertId;
    }
    
    public Object getSingleValueFromQuery(String sql) {
        Object result = null;
        try {
            PreparedStatement preparedStatement = connexion.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
    
            // Vérifier si le résultat contient des lignes
            if (resultSet.next()) {
                result = resultSet.getObject(1);  // Récupérer la première colonne de la première ligne
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'exécution de la requête SQL : " + e.getMessage());
            e.printStackTrace();
        }
        return result;
    }
       
    // Méthode pour fermer la connexion
    public void close() {
        try {
            if (connexion != null && !connexion.isClosed()) {
                connexion.close();
                System.out.println("Connexion fermée.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
