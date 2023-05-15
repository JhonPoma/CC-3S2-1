package removecontrolflag;

// Antes
/*
public class Busqueda {
    boolean busquedaLineal(int[] arr, int elemento) {
        boolean encontrado = false;
        for (int i = 0; i < arr.length; i++) {
            if (!encontrado) {
                if (arr[i] == elemento) {
                    encontrado = true;
                }
            }

        }
        return encontrado;
    }
}
*/

// Después
public class Busqueda {
    boolean busquedaLineal(int[] arr, int elemento) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == elemento) {
                return true;
            }
        }
        return false;
    }
}
