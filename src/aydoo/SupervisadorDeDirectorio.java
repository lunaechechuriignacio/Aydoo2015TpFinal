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
import java.util.concurrent.TimeUnit;

public class SupervisadorDeDirectorio {
	private Path directorioSupervisado;
	private WatchService servicio;
	private ProcesadorEstadisticoDaemon procesadorDaemon;
	private Path fullPath;

	public SupervisadorDeDirectorio(String directorio,
			ProcesadorEstadisticoDaemon procesadorDaemon) {
		this.directorioSupervisado = Paths.get(directorio);
		this.procesadorDaemon = procesadorDaemon;
		
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

	public void correrSupervisadorDeDirectorio(int ciclo) {
		this.crearEventoWatch();
		try {
			WatchKey key = null;
			while (ciclo > 0) {
				ciclo--;

				key = servicio.poll(10, TimeUnit.SECONDS);
				
				if (key != null) {
					Thread.sleep(3000);
					this.ejecutarEvento(key);

					if (!key.reset()) {
						break;
					}
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void ejecutarEvento(WatchKey key) {
		Kind<?> kind = null;
		for (WatchEvent<?> watchEvent : key.pollEvents()) {

			kind = watchEvent.kind();
			if (OVERFLOW == kind) {
				continue;
			} else if (ENTRY_CREATE == kind) {

				this.procesarArchivoAgregado(watchEvent, key);
			}
		}
	}

	private void procesarArchivoAgregado(WatchEvent<?> watchEvent, WatchKey key) {
		String extencionDeArchivo = watchEvent.context().toString()
				.substring(watchEvent.context().toString().length() - 3);
		@SuppressWarnings("unchecked")
		WatchEvent<Path> ev = (WatchEvent<Path>) watchEvent;
		Path dir = (Path) key.watchable();
		this.fullPath = dir.resolve(ev.context());
		if (extencionDeArchivo.equals("zip"))
			this.procesadorDaemon.generarInforme(fullPath.toString());

	}

	public Path getFullPath() {
		return fullPath;
	}

}
