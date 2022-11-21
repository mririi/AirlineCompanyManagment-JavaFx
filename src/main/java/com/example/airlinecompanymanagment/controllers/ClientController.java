package com.example.airlinecompanymanagment.controllers;

import com.example.airlinecompanymanagment.models.Airport;
import com.example.airlinecompanymanagment.models.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

import static java.lang.Integer.parseInt;

public class ClientController implements Initializable {
    Connection conn = null;

    public ClientController() {
        conn = SingletonConnection.getConnection();
    }
    @FXML
    private Button addbtn;
    @FXML
    private TableColumn<?, ?> passportcol;

    @FXML
    private TextField passportinput;
    @FXML
    private TextField addressInput;

    @FXML
    private TableColumn<Person, String> addresscol;

    @FXML
    private DatePicker birthInput;

    @FXML
    private TableColumn<Person, String> birthcol;

    @FXML
    private Button deletebtn;

    @FXML
    private TextField emailInput;

    @FXML
    private TableColumn<Person, String> emailcol;

    @FXML
    private TextField firstnameInput;

    @FXML
    private TableColumn<Person, String> firstnamecol;

    @FXML
    private TextField lastnameInput;

    @FXML
    private TableColumn<Person, String> lastnamecol;

    @FXML
    private Button modifybtn;

    @FXML
    private Text nbC;

    @FXML
    private TextField phoneInput;

    @FXML
    private TableColumn<Person, String> phonecol;

    @FXML
    private TableView<Person> tab;

    ObservableList<Person> list = FXCollections.observableArrayList();

    @FXML
    void OnSet(MouseEvent event) {
        String np;
        String fn;
        String ln;
        String ad;
        String em;
        int tel;
        Date birth;

        np= tab.getSelectionModel().getSelectedItem().getNpassport();
        ln = tab.getSelectionModel().getSelectedItem().getLastName();
        fn = tab.getSelectionModel().getSelectedItem().getFirstName();
        ad = tab.getSelectionModel().getSelectedItem().getAddress();
        em = tab.getSelectionModel().getSelectedItem().getEmail();
        tel = tab.getSelectionModel().getSelectedItem().getTel();
        birth = (Date) tab.getSelectionModel().getSelectedItem().getBirthDate();

        passportinput.setText(np);
        firstnameInput.setText(String.valueOf(fn));
        lastnameInput.setText(String.valueOf(ln));
        addressInput.setText(String.valueOf(ad));
        emailInput.setText(String.valueOf(em));
        phoneInput.setText(String.valueOf(tel));
        birthInput.setValue(LocalDate.parse(String.valueOf(birth)));


    }
    @FXML
    void onAdd(ActionEvent event) throws SQLException, ParseException {
        if (this.isInputValid()) {
            String pass=passportinput.getText();
            String first = firstnameInput.getText();
            String last = lastnameInput.getText();
            String address = addressInput.getText();
            String tel = phoneInput.getText();
            String email = emailInput.getText();
            Date birth = Date.valueOf(birthInput.getValue());
            PreparedStatement x = (PreparedStatement) conn.prepareStatement("insert into Client(npassport,lastname,firstname,address,tel,email,birthdate) values(?,?,?,?,?,?,?)");

            x.setString(1, pass);
            x.setString(2, last);
            x.setString(3, first);
            x.setString(4,address );
            x.setInt(5, parseInt(tel));
            x.setString(6, email);
            x.setDate(7, birth);
            x.execute();

            Person c = new Person(pass,this.lastnameInput.getText(),this.firstnameInput.getText(), this.addressInput.getText(),parseInt(this.phoneInput.getText()),this.emailInput.getText(),Date.valueOf(birthInput.getValue()));
            tab.getItems().add(c);
            nbC.setText(String.valueOf(list.size()));
            this.clearInput();
        }



    }
    private void clearInput() {
        this.passportinput.setText("");
        this.firstnameInput.setText("");
        this.lastnameInput.setText("");
        this.addressInput.setText("");
        this.phoneInput.setText("");
        this.emailInput.setText("");
        this.birthInput.setValue(null);

    }

    private boolean isInputValid() {
        if (this.passportinput.getText().isEmpty() || this.firstnameInput.getText().isEmpty() || this.lastnameInput.getText().isEmpty() || this.addressInput.getText().isEmpty() || this.phoneInput.getText().isEmpty() || this.emailcol.getText().isEmpty() || this.birthcol.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Input error!");
            alert.setHeaderText(null);
            alert.setContentText("Fields can't be empty!");
            alert.show();
            return false;
        }
        try {
            Integer.parseInt(this.phoneInput.getText());
        } catch(NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Input error!");
            alert.setHeaderText(null);
            alert.setContentText("Phone has to be a number!");
            alert.show();
            return false;
        }
        if (this.passportinput.getText().length()<8) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Input error!");
            alert.setHeaderText(null);
            alert.setContentText("Passport number length has to be 8!");
            alert.show();
            return false;
        }
        return true;
    }
    @FXML
    void onDelete(ActionEvent event) throws SQLException {
        Alert alert= new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setContentText("Are you sure to delete ?");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get()==ButtonType.OK) {
            int idC;
            idC = tab.getSelectionModel().getSelectedItem().getIdP();
            tab.getItems().remove(tab.getSelectionModel().getSelectedItem());
            PreparedStatement x = (PreparedStatement) conn.prepareStatement("delete from client where idC=" + idC);
            x.execute();
            nbC.setText(String.valueOf(list.size()));
        }}
    @FXML
    void onModify(ActionEvent event) throws SQLException {
        if (this.isInputValid()) {

            int idC;
            idC = tab.getSelectionModel().getSelectedItem().getIdP();
            String pass =passportinput.getText();
            String first = firstnameInput.getText();
            String last = lastnameInput.getText();
            String address = addressInput.getText();
            String tel = phoneInput.getText();
            String email = emailInput.getText();
            Date birth = Date.valueOf(birthInput.getValue());
            PreparedStatement x = (PreparedStatement) conn.prepareStatement("update client set npassport='"+pass+"',firstname='" + first +"',lastname='"+last+"',address='"+address+"',tel='"+tel+"',email='"+email+"',birthdate='"+birth+"' where idC='" + idC + "' ");
            x.execute();
            clearInput();
            list.clear();
            ResultSet rs = conn.createStatement().executeQuery("select * from client");
            while (rs.next()) {
                list.add(new Person(rs.getInt("idC"),rs.getString("npassport"),rs.getString("firstname"), rs.getString("lastname"), rs.getString("address"), rs.getInt("tel"), rs.getString("email"), rs.getDate("birthdate")));
            }
            passportcol.setCellValueFactory(new PropertyValueFactory<>("npassport"));
            firstnamecol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
            lastnamecol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
            addresscol.setCellValueFactory(new PropertyValueFactory<>("address"));
            phonecol.setCellValueFactory(new PropertyValueFactory<>("tel"));
            emailcol.setCellValueFactory(new PropertyValueFactory<>("email"));
            birthcol.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
            tab.setItems(list);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        list.clear();
        ResultSet rs = null;
        try {
            rs = conn.createStatement().executeQuery("select * from client");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        while (true) {
            try {
                if (!rs.next()) break;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                list.add(new Person(rs.getInt("idC"),rs.getString("npassport"),rs.getString("lastname"),rs.getString("firstname"),  rs.getString("address"), rs.getInt("tel"), rs.getString("email"), rs.getDate("birthdate")));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        passportcol.setCellValueFactory(new PropertyValueFactory<>("npassport"));
        lastnamecol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        firstnamecol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        addresscol.setCellValueFactory(new PropertyValueFactory<>("address"));
        phonecol.setCellValueFactory(new PropertyValueFactory<>("tel"));
        emailcol.setCellValueFactory(new PropertyValueFactory<>("email"));
        birthcol.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
        tab.setItems(list);
        nbC.setText(String.valueOf(list.size()));
    }
}
