package aydoo;


import org.junit.Assert;
import org.junit.Test;
import java.util.TreeMap;

public class BiciUtilizadaMasTiempoTest {

    @Test
    public void siElMapaEstaVacioDevuelveTiempoMaximoCero(){


        TreeMap<String, Integer> mapaTiemposPromedio = new TreeMap();
        EstadisticaBiciUsadaMasTiempo estadisticaBiciUsadaMasTiempo = new EstadisticaBiciUsadaMasTiempo();
        estadisticaBiciUsadaMasTiempo.calcularTiempoBicicletaMasUsadayTiempoMaximoRecorrido(mapaTiemposPromedio);
        Assert.assertEquals(estadisticaBiciUsadaMasTiempo.getTiempoMaximoRecorrido(), 0.0,0);
    }

    @Test
    public void siElMapaEstaVacioDevuelveListaIdBiciMasUsadaVacia(){


        TreeMap<String, Integer> mapaTiemposPromedio = new TreeMap();
        EstadisticaBiciUsadaMasTiempo estadisticaBiciUsadaMasTiempo = new EstadisticaBiciUsadaMasTiempo();
        estadisticaBiciUsadaMasTiempo.calcularTiempoBicicletaMasUsadayTiempoMaximoRecorrido(mapaTiemposPromedio);
        Assert.assertTrue(estadisticaBiciUsadaMasTiempo.getListaIdBicicletaMaximoRecorrido().isEmpty());
    }

    @Test
    public void siElMapaTieneUnaSolaEntradaElIdDebeSerElDeEsaEntrada(){


        TreeMap<String, Integer> mapaTiemposPromedio = new TreeMap();
        mapaTiemposPromedio.put("1", 5);
        EstadisticaBiciUsadaMasTiempo estadisticaBiciUsadaMasTiempo = new EstadisticaBiciUsadaMasTiempo();
        estadisticaBiciUsadaMasTiempo.calcularTiempoBicicletaMasUsadayTiempoMaximoRecorrido(mapaTiemposPromedio);
        Assert.assertEquals("1", estadisticaBiciUsadaMasTiempo.getListaIdBicicletaMaximoRecorrido().get(0));
    }

    @Test
    public void siElMapaTieneUnaSolaEntradaElTiempoDebeSerElDeEsaEntrada(){


        TreeMap<String, Integer> mapaTiemposPromedio = new TreeMap();
        EstadisticaBiciUsadaMasTiempo estadisticaBiciUsadaMasTiempo = new EstadisticaBiciUsadaMasTiempo();
        mapaTiemposPromedio.put("1",5);
        estadisticaBiciUsadaMasTiempo.calcularTiempoBicicletaMasUsadayTiempoMaximoRecorrido(mapaTiemposPromedio);
        Assert.assertEquals(estadisticaBiciUsadaMasTiempo.getTiempoMaximoRecorrido(), 5.0, 0);
    }


    @Test
    public void conElMapaDadoElTiempoMaximoDeUsoDebeSer90(){


        TreeMap<String, Integer> mapaTiemposPromedio = new TreeMap();
        EstadisticaBiciUsadaMasTiempo estadisticaBiciUsadaMasTiempo = new EstadisticaBiciUsadaMasTiempo();
        mapaTiemposPromedio.put("1",5);
        mapaTiemposPromedio.put("2",50);
        mapaTiemposPromedio.put("2",30);
        mapaTiemposPromedio.put("5",90);
        mapaTiemposPromedio.put("2",5);
        mapaTiemposPromedio.put("5",90);
        mapaTiemposPromedio.put("1",90);
        mapaTiemposPromedio.put("3",9);
        mapaTiemposPromedio.put("15",80);
        mapaTiemposPromedio.put("555",20);
        mapaTiemposPromedio.put("4",10);
        mapaTiemposPromedio.put("2",15);
        estadisticaBiciUsadaMasTiempo.calcularTiempoBicicletaMasUsadayTiempoMaximoRecorrido(mapaTiemposPromedio);
        Assert.assertEquals(90.0, estadisticaBiciUsadaMasTiempo.getTiempoMaximoRecorrido(), 0);
    }
}
