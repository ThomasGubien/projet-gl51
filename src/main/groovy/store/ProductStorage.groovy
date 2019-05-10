package store

interface ProductStorage {

    List<Product> productList
    int maxStorageSize

    //Lister les produits
    List<Product> getAllProducts()

    //Selectionner les produits d'un type donné
    List<Product> getAllProductsFromType(int i)

    //Trier la liste par type
    int orderByType()

    //Assez de place dans le stock
    boolean isThereEnoughSpace()

    //Ajouter produit
    void store(Product p)

    //Retirer produit
    int delete(int i)

    //Mettre à jour un produit
    int modify(int i, Product p)

}