package viewmodel;

import javafx.beans.property.*;
import model.LocalModel;

import java.time.LocalDate;

public class EditBabysitterViewModel
{
    private LocalModel model;
    private ViewState viewState;
    private StringProperty firstName;
    private StringProperty lastName;
    private StringProperty username;
    private SimpleObjectProperty<LocalDate> age;
    private DoubleProperty babysittingExperience;
    private DoubleProperty paymentPerHour;
    private StringProperty language1;
    private StringProperty language2;
    private StringProperty language3;
    private StringProperty language4;
    private ObjectProperty<Boolean> hasCertificate;
    private StringProperty errorLabel;

    public EditBabysitterViewModel(LocalModel model, ViewState viewState)
    {
        this.model = model;
        this.viewState = viewState;
        firstName = new SimpleStringProperty();
        lastName = new SimpleStringProperty();
        username = new SimpleStringProperty();
        age = new SimpleObjectProperty<LocalDate>();
        babysittingExperience = new SimpleDoubleProperty();
        paymentPerHour = new SimpleDoubleProperty();
        language1 = new SimpleStringProperty();
        language2 = new SimpleStringProperty();
        language3 = new SimpleStringProperty();
        language4 = new SimpleStringProperty();
        hasCertificate = new SimpleObjectProperty<>();
        errorLabel = new SimpleStringProperty();
    }

    public void reset()
    {
        loadData();
        errorLabel.set("");
    }

    private void loadData()
    {
        firstName.set(viewState.getAccount().getFirstName());
        lastName.set(viewState.getAccount().getLastName());
        username.set(viewState.getAccount().getUserName());
        age.setValue(LocalDate.ofEpochDay(viewState.getBabysitter().getAge()));
        babysittingExperience
            .set(viewState.getBabysitter().getBabysittingExperience());
        paymentPerHour.set(viewState.getBabysitter().getPaymentPerHour());
        language1.set(viewState.getBabysitter().getMainLanguage());
//        language2.set(viewState.getBabysitter().getLanguages().get(1));
//        language3.set(viewState.getBabysitter().getLanguages().get(2));
//        language4.set(viewState.getBabysitter().getLanguages().get(3));
        hasCertificate.set(viewState.getBabysitter().hasFirstAidCertificate());
    }

    public StringProperty getFirstName()
    {
        return firstName;
    }

    public StringProperty getLastName()
    {
        return lastName;
    }

    public StringProperty getUsername()
    {
        return username;
    }

    public ObjectProperty<LocalDate> getAge()
    {
        return age;
    }

    public DoubleProperty getBabysittingExperience()
    {
        return babysittingExperience;
    }

    public DoubleProperty getPaymentPerHour()
    {
        return paymentPerHour;
    }

    public StringProperty getLanguage1()
    {
        return language1;
    }

    public StringProperty getLanguage2()
    {
        return language2;
    }

    public StringProperty getLanguage3()
    {
        return language3;
    }

    public StringProperty getLanguage4()
    {
        return language4;
    }

    public ObjectProperty<Boolean> getHasCertificate()
    {
        return hasCertificate;
    }

    public StringProperty getError()
    {
        return errorLabel;
    }
}
