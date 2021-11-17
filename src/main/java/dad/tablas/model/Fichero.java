package dad.tablas.model;

import java.io.File;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Fichero {

	private ObjectProperty<File> fichero = new SimpleObjectProperty<>();
	private StringProperty nombre = new SimpleStringProperty();
	private LongProperty tamano = new SimpleLongProperty();
	private ObjectProperty<TipoFichero> tipo = new SimpleObjectProperty<>();
	private ObjectProperty<LocalDateTime> ultimaModificacion = new SimpleObjectProperty<>();
	private BooleanProperty ejecutable = new SimpleBooleanProperty();

	public Fichero(File f) {

		LocalDateTime ultimaModificacion = LocalDateTime.ofEpochSecond(f.lastModified() / 1000, 0,
				OffsetDateTime.now().getOffset());

		setFichero(f);
		setNombre(f.getName());
		setTamano(f.length());
		setTipo(f.isDirectory() ? TipoFichero.DIRECOTRIO : TipoFichero.FICHERO);
		setUltimaModificacion(ultimaModificacion);
		setEjecutable(f.canExecute());
	}

	public final ObjectProperty<File> ficheroProperty() {
		return this.fichero;
	}

	public final File getFichero() {
		return this.ficheroProperty().get();
	}

	public final void setFichero(final File fichero) {
		this.ficheroProperty().set(fichero);
	}

	public final StringProperty nombreProperty() {
		return this.nombre;
	}

	public final String getNombre() {
		return this.nombreProperty().get();
	}

	public final void setNombre(final String nombre) {
		this.nombreProperty().set(nombre);
	}

	public final LongProperty tamanoProperty() {
		return this.tamano;
	}

	public final long getTamano() {
		return this.tamanoProperty().get();
	}

	public final void setTamano(final long tamano) {
		this.tamanoProperty().set(tamano);
	}

	public final ObjectProperty<TipoFichero> tipoProperty() {
		return this.tipo;
	}

	public final TipoFichero getTipo() {
		return this.tipoProperty().get();
	}

	public final void setTipo(final TipoFichero tipo) {
		this.tipoProperty().set(tipo);
	}

	public final ObjectProperty<LocalDateTime> ultimaModificacionProperty() {
		return this.ultimaModificacion;
	}

	public final LocalDateTime getUltimaModificacion() {
		return this.ultimaModificacionProperty().get();
	}

	public final void setUltimaModificacion(final LocalDateTime ultimaModificacion) {
		this.ultimaModificacionProperty().set(ultimaModificacion);
	}

	public final BooleanProperty ejecutableProperty() {
		return this.ejecutable;
	}

	public final boolean isEjecutable() {
		return this.ejecutableProperty().get();
	}

	public final void setEjecutable(final boolean ejecutable) {
		this.ejecutableProperty().set(ejecutable);
	}

}
