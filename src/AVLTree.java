public class AVLTree {
    private Node root;
    public int peso;

    public AVLTree() {
        this.root = null;
    }

    public void insert(int value) {
        System.out.println("Valor a insertar "+ value);
        root = insertRec(root, value);
        peso++;
    }

    private int height(Node node){
        if(node == null){
            return 0;
        }
        return node.getAltura();
    }

    private Node insertRec(Node node, int value) {
        if (node == null) {
            Node newNode = new Node(value);
            newNode.setAltura(1);
            System.out.println("Nodo insertado: "+newNode.getValue()+" balance al insertar= " +getBalance(newNode));
            return newNode;
        }
        if (value < node.getValue()) {
            // ME VOY A LA IZQUIERDA
            node.setLeft(insertRec(node.getLeft(), value));
        } else if (value > node.getValue()) {
            // ME VOY A LA DERECHA
            node.setRight(insertRec(node.getRight(), value));
        }else{
            return node;
        }

        System.out.println("Nodo actual: "+node.getValue());


        // actualizar la altura de este ancestro node
        int altura = 1+Math.max(height(node.getLeft()),height(node.getRight()));
        node.setAltura(altura);
        System.out.println("\tAltura= "+node.getAltura());
        
        int balance = getBalance(node);
        System.out.println("\tBalance= "+balance);

        // Si el nodo se desbalancea, hay 4 casos a considerar

        // 1. Caso Izquierda-Izquierda (Rotación simple a la derecha)
        if (balance > 1 && value < node.getLeft().getValue()) {
            System.out.println("Rotacion Derecha (Simple) en el nodo " + node.getValue());
            return rightRotate(node);
        }

        // 2. Caso Derecha-Derecha (Rotación simple a la izquierda)
        if (balance < -1 && value > node.getRight().getValue()) {
            System.out.println("Rotacion Izquierda (Simple) en el nodo " + node.getValue());
            return leftRotate(node);
        }

        // 3. Caso Izquierda-Derecha (Rotación doble)
        if (balance > 1 && value > node.getLeft().getValue()) {
            System.out.println("Rotacion Izquierda-Derecha (Doble) en el nodo " + node.getValue());
            node.setLeft(leftRotate(node.getLeft()));
            return rightRotate(node);
        }

        // 4. Caso Derecha-Izquierda (Rotación doble)
        if (balance < -1 && value < node.getRight().getValue()) {
            System.out.println("Rotacion Derecha-Izquierda (Doble) en el nodo " + node.getValue());
            node.setRight(rightRotate(node.getRight()));
            return leftRotate(node);
        }

        // Retorna el puntero del nodo (sin cambios o después de rotar)
        return node;
    }

        public int getBalance(Node node){
            if (node == null)return 0;
            
            return height(node.getLeft()) - height(node.getRight());
        }

    private Node rightRotate(Node y) {
        Node x = y.getLeft();
        Node T2 = x.getRight();

        // Realizar rotación
        x.setRight(y);
        y.setLeft(T2);

        // Actualizar alturas
        y.setAltura(1 + Math.max(height(y.getLeft()), height(y.getRight())));
        x.setAltura(1 + Math.max(height(x.getLeft()), height(x.getRight())));

        // Retornar nueva raíz
        return x;
    }

    private Node leftRotate(Node x) {
        Node y = x.getRight();
        Node T2 = y.getLeft();

        // Realizar rotación
        y.setLeft(x);
        x.setRight(T2);

        // Actualizar alturas
        x.setAltura(1 + Math.max(height(x.getLeft()), height(x.getRight())));
        y.setAltura(1 + Math.max(height(y.getLeft()), height(y.getRight())));

        // Retornar nueva raíz
        return y;
    }
    

    
    public boolean eq() {
        return eqRec(root);
    }

    private boolean eqRec(Node node) {
        if (node != null) {
            if (getBalance(node) == 0 || getBalance(node) == -1
                    || getBalance(node) == 1) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
}
