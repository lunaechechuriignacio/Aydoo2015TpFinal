package aydoo;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.OVERFLOW;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

public class SupervisadorDeDirectorio {
	private final Path directorioSupervisado;
	private WatchService servicio;
	private final ProcesadorEstadisticoDaemon procesadorDaemon;
	

	public SupervisadorDeDirectorio(String directorio ,ProcesadorEstadisticoDaemon procesadorDaemon) {
		this.directorioSupervisado = Paths.get(directorio);
		this.procesadorDaemon= procesadorDaemon;

	}

	private void crearEventoWatch() {
		try {
			this.servicio = this.directorioSupervisado.getFileSystem()
					.newWatchService();
			this.directorioSupervisado.register(servicio, ENTRY_CREATE);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void correrSupervisadorDeDirectorio() {
		this.crearEventoWatch();
		try {
			WatchKey key = null;
			while (true) {
				key = servicio.take();

				Kind<?> kind = null;
				for (WatchEvent<?> watchEvent : key.pollEvents()) {

					kind = watchEvent.kind();
					if (OVERFLOW == kind) {
						continue;
					} else if (ENTRY_CREATE == kind) {
						String extencionDeArchivo=watchEvent.context().toString().substring(watchEvent.context().toString().length()-3);
						if (extencionDeArchivo.equals("zip"))
						this.procesadorDaemon.generarInforme();
					}
				}

				if (!key.reset()) {
					break;
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public WatchService getServicio() {
		return servicio;
	}

}
