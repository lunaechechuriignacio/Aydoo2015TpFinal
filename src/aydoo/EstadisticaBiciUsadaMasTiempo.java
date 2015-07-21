package aydoo;


import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

public class EstadisticaBiciUsadaMasTiempo {
    private float tiempoMaximoRecorrido;
    private List<String> listaIdBicicletaMaximoRecorrido;

    public EstadisticaBiciUsadaMasTiempo(){
        this.listaIdBicicletaMaximoRecorrido = new LinkedList<String>();
    }

    public void calcularTiempoBicicletaMasUsadayTiempoMaximoRecorrido(TreeMap<String, Integer> mapaTiemposPromedio){

        tiempoMaximoRecorrido = 0;

        for (String id: mapaTiemposPromedio.keySet()){

            if (mapaTiemposPromedio.get(id) >= tiempoMaximoRecorrido){

                if (mapaTiemposPromedio.get(id) > tiempoMaximoRecorrido){
                    listaIdBicicletaMaximoRecorrido.clear();
                }

                listaIdBicicletaMaximoRecorrido.add(id);
                tiempoMaximoRecorrido = mapaTiemposPromedio.get(id);

            }
        }
    }

    public float getTiempoMaximoRecorrido() {
        return tiempoMaximoRecorrido;
    }

    public List<String> getListaIdBicicletaMaximoRecorrido() {
        return listaIdBicicletaMaximoRecorrido;
    }
}
