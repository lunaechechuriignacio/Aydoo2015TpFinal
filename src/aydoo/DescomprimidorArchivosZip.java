package aydoo;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class DescomprimidorArchivosZip {

	private String pathZip;
	private final String pathSalida;
	private File directorio;

	public DescomprimidorArchivosZip(String pathZip, String pathDeSalida) {
		this.pathZip = pathZip;
		this.pathSalida = pathDeSalida;

	}

	public void crearDirectorio(String directorioACrear) {

		this.directorio = new File(pathSalida);

		this.directorio.mkdir();

	}

	public void setPathZip(String pathZip) {
		this.pathZip = pathZip;
	}

	public File getDirectorio() {

		return this.directorio;
	}

	public void descomprimirArchivosZip() throws IOException {

		FileInputStream archivoZip = new FileInputStream(this.pathZip);
		ZipInputStream zipStream = new ZipInputStream(new BufferedInputStream(
				archivoZip));
		ZipEntry entry;

		while ((entry = zipStream.getNextEntry()) != null) {

			int size;
			byte[] buffer = new byte[2048];
			this.crearDirectorio(entry.getName());
			FileOutputStream archivoDescomprimido = new FileOutputStream(
					this.directorio.toString() + "/" + entry.getName());

			BufferedOutputStream bufferDeSalida = new BufferedOutputStream(
					archivoDescomprimido, buffer.length);

			while ((size = zipStream.read(buffer, 0, buffer.length)) != -1) {
				bufferDeSalida.write(buffer, 0, size);
			}
			bufferDeSalida.flush();
			bufferDeSalida.close();
		}
		zipStream.close();
		archivoZip.close();
	}
}
