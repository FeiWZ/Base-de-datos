import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Postales {
    public static void main(String[] args) {
        // Identificar el archivo de texto que se deberá de leer
        String doccsv = "codigos_postales_hmo.csv";
        // Mapa para almacenar el código postal (String) y el número de asentamientos (Integer)
        Map<String, Integer> contador = new HashMap<>();
        // Abrimos un try-catch para que se puedan identificar errores y luego usaremos BufferedReader para leer el archivo de texto
        try (BufferedReader br = new BufferedReader(new FileReader(doccsv))) {
            String linea;
            // Abrimos un while que leera el documento línea por línea
            while ((linea = br.readLine()) != null) {
                // Especificar que los datos se separan por comas
                String[] datos = linea.split(",");
                // el if es para comprobar que cada línea del archivo tiene al menos 2 columnas
                if (datos.length >= 2) {
                    String codigopostal = datos[0].trim();
                    // Cuando el contador identifica un código postal repetido se le agrega al contador
                    contador.put(codigopostal, contador.getOrDefault(codigopostal, 0) + 1);
                }
            }
            // Imprimimos los resultados
            System.out.println("Resultado de los datos:");
            // Creamos una entrada de mapa para que salga cada Key y Value
            // Comparamos cada código postal para que salgan en orden y sea más legible
            contador.entrySet().stream().sorted(Map.Entry.comparingByKey((postal1, postal2) -> 
            Integer.compare(Integer.parseInt(postal1), Integer.parseInt(postal2)))).forEach(entrada -> 
            // Usamos printf para que imprime formateando cada resultado
            // %s es reemplazado por el código postal, %d por el número de asentamientos y $n es un salto de línea
            System.out.printf("Código postal: %s - Número de asentamientos: %d%n", entrada.getKey(), entrada.getValue()));

        } catch (IOException e) {
            System.out.println("ERROR. No se pudo leer el archivo.");
        }
    }
}
