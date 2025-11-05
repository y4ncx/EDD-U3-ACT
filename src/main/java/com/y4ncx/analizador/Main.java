package com.y4ncx.analizador;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;


public class Main {
    public static void main(String[] args) {
        Path entrada;
        Path salidaEstadisticas;
        Path salidaOrdenado;

        // Archivos por defecto si no se pasan argumentos
        if (args.length < 3) {
            System.out.println("⚠️  Advertencia: Se detectaron menos de 3 argumentos. Usando archivos por defecto para pruebas locales.");
            entrada = Paths.get("src/main/resources/numeros.txt");
            salidaEstadisticas = Paths.get("estadisticas.txt");
            salidaOrdenado = Paths.get("ordenado.txt");
        } else {
            entrada = Paths.get(args[0]);
            salidaEstadisticas = Paths.get(args[1]);
            salidaOrdenado = Paths.get(args[2]);
        }

        try {
            // Verificar existencia del archivo
            if (!Files.exists(entrada)) {
                System.err.println("❌ Archivo de entrada no encontrado: " + entrada.toAbsolutePath());
                System.exit(2);
            }

            // === Lectura y análisis numérico ===
            List<Double> datos = AnalizadorNumeros.LeerNumerosDesdeArchivo(entrada);
            if (datos.isEmpty()) {
                System.err.println("⚠️  El archivo no contiene números válidos.");
                System.exit(2);
            }

            // Generar estadísticas
            String resumen = AnalizadorNumeros.generarResumen(datos);
            Files.writeString(salidaEstadisticas, resumen);

            // Ordenar y guardar
            List<Double> ordenado = AnalizadorNumeros.ordenar(datos, true);
            AnalizadorNumeros.guardarListaEnArchivo(ordenado, salidaOrdenado);

            // Mostrar resultados básicos
            System.out.println("✅ Análisis completado correctamente.\n");
            System.out.println(resumen);
            System.out.println("📊 Archivos generados:");
            System.out.println(" - Estadísticas: " + salidaEstadisticas.toAbsolutePath());
            System.out.println(" - Números ordenados: " + salidaOrdenado.toAbsolutePath());

            // === Operaciones adicionales sobre colecciones ===
            System.out.println("\n🔹 Pruebas de OperacionesColecciones:");

            List<Double> redondeados = OperacionesColecciones.transformarRedondeo(datos);
            System.out.println("Ejemplo (redondeados): " + redondeados.subList(0, Math.min(5, redondeados.size())));

            List<Double> positivos = OperacionesColecciones.filtrarPositivos(datos);
            System.out.println("Ejemplo (positivos): " + positivos.subList(0, Math.min(5, positivos.size())));

            Map<Double, Long> frecuencias = OperacionesColecciones.contarFrecuencias(redondeados);
            System.out.println("\nFrecuencias (valor - repeticiones):");
            Utilidades.imprimirMapa(frecuencias);

            // === Benchmark comparativo ===
            System.out.println("\n⚙️  Comparando rendimiento de colecciones...");
            Bench.compararRendimiento();

            System.out.println("\n✅ Programa finalizado con éxito.");

        } catch (IOException e) {
            System.err.println("💥 Error de entrada/salida: " + e.getMessage());
            System.exit(3);
        } catch (NumberFormatException e) {
            System.err.println("⚠️  Formato de número inválido en el archivo: " + e.getMessage());
            System.exit(4);
        } catch (Exception e) {
            System.err.println("🚨 Error inesperado: " + e.getMessage());
            System.exit(5);
        }
    }
}
