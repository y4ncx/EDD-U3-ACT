package com.y4ncx.analizador;

import java.util.*;
import java.util.stream.Collectors;

public class OperacionesColecciones {

    //1. Transformacion (map)
    public static List<Double> transformarRedondeo(List<Double> datos) {
        return datos.stream()
                .map(n -> Math.round(n * 100.0) / 100.0)
                .collect(Collectors.toList());
    }

    //2. Filtrado (filter)
    public static List<Double> filtrarPositivos(List<Double> datos) {
        return datos.stream()
                .filter(n -> n > 0)
                .collect(Collectors.toList());
    }

    //3. Ordenacion (sort)
    public static List<Double> ordenarDesc(List<Double> datos) {
        return datos.stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }


    //4. Conjuntos (Set operations)
    public static List<Double> union(Set<Double> a, Set<Double> b) {
        Set<Double> resultado = new HashSet<>(a);
        resultado.addAll(b);
        return new ArrayList<>(resultado);
    }

    public static Set<Double> interseccion(Set<Double> a, Set<Double> b) {
        Set<Double> resultado = new HashSet<>(a);
        resultado.retainAll(b);
        return resultado;
    }

    public static Set<Double> diferencia(Set<Double> a, Set<Double> b) {
        Set<Double> resultado = new HashSet<>(a);
        resultado.removeAll(b);
        return resultado;
    }

    //5. Mapas/Diccionarios (conteo de frecuencia)
    public static Map<Double, Long> contarFrecuencias(List<Double> datos) {
        return datos.stream()
                .collect(Collectors.groupingBy( n -> n, Collectors.counting()));
    }

    //6. Busqueda indexada con Map
    public static Map<Integer, Double> crearIndice(List<Double> datos) {
        Map<Integer, Double> indice = new HashMap<>();
        for (int i = 0; i < datos.size(); i++) {
            indice.put(i, datos.get(i));
        }
        return indice;
    }

}
