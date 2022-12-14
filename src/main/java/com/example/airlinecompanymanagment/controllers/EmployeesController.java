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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class EmployeesController implements Initializable {
    Connection conn = null;

    public EmployeesController() {
        conn = SingletonConnection.getConnection();
    }
    @FXML
    private Button addbtn;

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
    private TextField salaryInput;
    @FXML
    private TextField departmentInput;

    @FXML
    private TableColumn<Person, String> departmentcol;

    @FXML
    private TableColumn<Person, String> salarycol;

    @FXML
    private TableView<Person> tab;
    @FXML
    private ComboBox<String> combo;

    ObservableList<Person> list = FXCollections.observableArrayList();

    @FXML
    void OnSet(MouseEvent event) {
        String fn;
        String ln;
        String ad;
        String em;
        int tel;
        Date birth;
        String dep;
        double sal;

        ln = tab.getSelectionModel().getSelectedItem().getLastName();
        fn = tab.getSelectionModel().getSelectedItem().getFirstName();
        ad = tab.getSelectionModel().getSelectedItem().getAddress();
        em = tab.getSelectionModel().getSelectedItem().getEmail();
        tel = tab.getSelectionModel().getSelectedItem().getTel();
        birth = (Date) tab.getSelectionModel().getSelectedItem().getBirthDate();
        dep = tab.getSelectionModel().getSelectedItem().getIdDep();
        sal = tab.getSelectionModel().getSelectedItem().getSalary();



        firstnameInput.setText(String.valueOf(fn));
        lastnameInput.setText(String.valueOf(ln));
        addressInput.setText(String.valueOf(ad));
        emailInput.setText(String.valueOf(em));
        phoneInput.setText(String.valueOf(tel));
        birthInput.setValue(birth.toLocalDate());
        combo.setValue(dep);
        salaryInput.setText(String.valueOf(sal));


    }
    @FXML
    void onAdd(ActionEvent event) throws SQLException, ParseException {
        if (this.isInputValid()) {
            String first = firstnameInput.getText();
            String last = lastnameInput.getText();
            String address = addressInput.getText();
            String tel = phoneInput.getText();
            String email = emailInput.getText();
            Date birth = Date.valueOf(birthInput.getValue());
            String department = combo.getValue();;
            String salary = salaryInput.getText();
            PreparedStatement x = (PreparedStatement) conn.prepareStatement("insert into Employee(lastname,firstname,address,tel,email,birthdate,salary,idDep) values(?,?,?,?,?,?,?,?)");

            x.setString(1, last);
            x.setString(2, first);
            x.setString(3, address);
            x.setInt(4, parseInt(tel));
            x.setString(5, email);
            x.setDate(6, birth);
            x.setDouble(7,parseDouble(salary));
            ResultSet rs = conn.createStatement().executeQuery("Select idDep from department where name='"+department+"'");

            while (rs.next()) {
                x.setInt(8, rs.getInt("idDep"));
            }

            x.execute();

            Person c = new Person(this.lastnameInput.getText(),this.firstnameInput.getText(), this.addressInput.getText(),parseInt(this.phoneInput.getText()),this.emailInput.getText(),Date.valueOf(birthInput.getValue()),parseDouble(this.salaryInput.getText()),department);
            tab.getItems().add(c);
            nbC.setText(String.valueOf(list.size()));
            this.clearInput();
        }



    }
    private void clearInput() {
        this.firstnameInput.setText("");
        this.lastnameInput.setText("");
        this.addressInput.setText("");
        this.phoneInput.setText("");
        this.emailInput.setText("");
        this.birthInput.setValue(null);
        this.combo.setValue(null);
        this.salaryInput.setText("");
    }
    private boolean isInputValid() {
        if (this.firstnameInput.getText().isEmpty() || this.lastnameInput.getText().isEmpty() || this.addressInput.getText().isEmpty() || this.phoneInput.getText().isEmpty() || this.emailcol.getText().isEmpty() || this.birthcol.getText().isEmpty() || this.combo.getValue()==null || this.salarycol.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Input error!");
            alert.setHeaderText(null);
            alert.setContentText("Les champs ne doivent pas etre vide!");
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
        try {
            Double.parseDouble(this.salaryInput.getText());
        } catch(NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Input error!");
            alert.setHeaderText(null);
            alert.setContentText("Salary has to be a double!");
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
            PreparedStatement x = (PreparedStatement) conn.prepareStatement("delete from employee where idE=" + idC);
            x.execute();
            nbC.setText(String.valueOf(list.size()));
        }}
    @FXML
    void onModify(ActionEvent event) throws SQLException {
        if (this.isInputValid()) {

            int idC;
            idC = tab.getSelectionModel().getSelectedItem().getIdP();
            String first = firstnameInput.getText();
            String last = lastnameInput.getText();
            String address = addressInput.getText();
            String tel = phoneInput.getText();
            String email = emailInput.getText();
            String birth = String.valueOf(birthInput.getValue());
            String depart = combo.getValue();;
            String salary = salaryInput.getText();
            ResultSet rss = conn.createStatement().executeQuery("Select idDep from department where name='"+depart+"'");
            while (rss.next()) {
                PreparedStatement x = (PreparedStatement) conn.prepareStatement("update employee set firstname='" + first + "',lastname='" + last + "',address='" + address + "',tel='" + tel + "',email='" + email + "',salary='" + salary + "',idDep='" + rss.getInt("idDep") + "',birthdate='" + birth + "' where idE='" + idC + "'");
                x.execute();
            }
            clearInput();
            list.clear();
            ResultSet rs = conn.createStatement().executeQuery("select idE,lastname,firstname,address,tel,email,birthdate,salary,name from employee e,department d where e.idDep=d.idDep");
            while (rs.next()) {
                list.add(new Person(rs.getInt("idE"),rs.getString("firstname"), rs.getString("lastname"), rs.getString("address"), rs.getInt("tel"), rs.getString("email"), rs.getDate("birthdate"),rs.getDouble("salary"),rs.getString("name")));
            }
            firstnamecol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
            lastnamecol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
            addresscol.setCellValueFactory(new PropertyValueFactory<>("address"));
            phonecol.setCellValueFactory(new PropertyValueFactory<>("tel"));
            emailcol.setCellValueFactory(new PropertyValueFactory<>("email"));
            birthcol.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
            departmentcol.setCellValueFactory(new PropertyValueFactory<>("idDep"));
            salarycol.setCellValueFactory(new PropertyValueFactory<>("salary"));
            tab.setItems(list);
        }
    }
    @FXML
    List<String> oniddepartment () throws SQLException {

        List<String> options = new ArrayList<>();

        String query = "Select name from department";
        PreparedStatement st = (PreparedStatement) conn.prepareStatement(query);
        ResultSet rs = (ResultSet) st.executeQuery();
        while (rs.next()) {
            options.add(rs.getString("name"));
        }
        return options;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            combo.setItems(FXCollections.observableArrayList(oniddepartment()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        list.clear();
        ResultSet rs = null;
        try {
            rs = conn.createStatement().executeQuery("select idE,lastname,firstname,address,tel,email,birthdate,salary,name from employee e,department d where e.idDep=d.idDep");
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
                list.add(new Person(rs.getInt("idE"),rs.getString("lastname"),rs.getString("firstname"),  rs.getString("address"), rs.getInt("tel"), rs.getString("email"), rs.getDate("birthdate"),rs.getDouble("salary"),rs.getString("name")));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        lastnamecol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        firstnamecol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        addresscol.setCellValueFactory(new PropertyValueFactory<>("address"));
        phonecol.setCellValueFactory(new PropertyValueFactory<>("tel"));
        emailcol.setCellValueFactory(new PropertyValueFactory<>("email"));
        birthcol.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
        salarycol.setCellValueFactory(new PropertyValueFactory<>("salary"));
        departmentcol.setCellValueFactory(new PropertyValueFactory<>("idDep"));
        tab.setItems(list);
        nbC.setText(String.valueOf(list.size()));
    }
}
