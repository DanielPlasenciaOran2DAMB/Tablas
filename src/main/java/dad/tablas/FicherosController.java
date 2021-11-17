package dad.tablas;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.ResourceBundle;

import dad.tablas.model.Fichero;
import dad.tablas.model.TipoFichero;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.util.converter.LocalDateTimeStringConverter;

public class FicherosController implements Initializable {

	private StringProperty ruta = new SimpleStringProperty();
	private ListProperty<Fichero> ficheros = new SimpleListProperty<>(FXCollections.observableArrayList());
	private ObjectProperty<Fichero> seleccionado = new SimpleObjectProperty<>();

	@FXML
	private TableColumn<Fichero, Boolean> ejecutableColumn;

	@FXML
	private TableView<Fichero> ficherosTable;

	@FXML
	private Button listarbutton;

	@FXML
	private TableColumn<Fichero, LocalDateTime> modificacionColumn;

	@FXML
	private TableColumn<Fichero, String> nombreColumn;

	@FXML
	private TextField rutaText;

	@FXML
	private TableColumn<Fichero, Number> tamanoColumn;

	@FXML
	private TableColumn<Fichero, TipoFichero> tipoColumn;

	@FXML
	private BorderPane view;

	public FicherosController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Ficheros.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ruta.bindBidirectional(rutaText.textProperty());
		ficherosTable.itemsProperty().bind(ficheros);
		seleccionado.bind(ficherosTable.getSelectionModel().selectedItemProperty());

		nombreColumn.setCellValueFactory(v -> v.getValue().nombreProperty());
		tamanoColumn.setCellValueFactory(v -> v.getValue().tamanoProperty());
		tipoColumn.setCellValueFactory(v -> v.getValue().tipoProperty());
		modificacionColumn.setCellValueFactory(v -> v.getValue().ultimaModificacionProperty());
		ejecutableColumn.setCellValueFactory(v -> v.getValue().ejecutableProperty());

		modificacionColumn.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateTimeStringConverter()));
		ejecutableColumn.setCellFactory(CheckBoxTableCell.forTableColumn(ejecutableColumn));

		listar(new File("."));
	}

	@FXML
	void onListarAction(ActionEvent event) {
		listar(new File(ruta.get()));
	}

	private void listar(File path) {
		ruta.set(path.getAbsolutePath());
		ficheros.clear();

		if (path.isDirectory()) {
			Arrays.asList(path.listFiles()).stream().map(file -> new Fichero(file)).forEach(ficheros::add);
//			ficheros.setAll(Arrays.asList(f.listFiles()).stream().map(file -> new Fichero(file)).collect(Collectors.toList()));
		}
	}

	@FXML
	void onTablaClicker(MouseEvent event) {
		if (seleccionado.get() != null && seleccionado.get().getTipo().equals(TipoFichero.DIRECOTRIO)
				&& event.getClickCount() == 2) {
			listar(seleccionado.get().getFichero());
		}
	}

	public BorderPane getView() {
		return view;
	}
}
