package view;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import viewmodel.DoubleStringConverter;
import viewmodel.EditBabysitterViewModel;

public class EditBabysitterProfileViewController extends ViewController
{
    @FXML private TextField firstName;
    @FXML private TextField lastName;
    @FXML private TextField username;
    @FXML private DatePicker age;
    @FXML private TextField babysittingExperience;
    @FXML private TextField paymentPerHour;
    @FXML private TextField language1;
    @FXML private TextField language4;
    @FXML private TextField language2;
    @FXML private TextField language3;
    @FXML private RadioButton hasCertificate;
    @FXML private ToggleGroup groupToggle;
    @FXML private RadioButton doesNotHaveCertificate;
    @FXML private Label errorLabel;

    private EditBabysitterViewModel viewModel;

    @Override protected void init()
    {
        hasCertificate.setToggleGroup(groupToggle);
        doesNotHaveCertificate.setToggleGroup(groupToggle);
        viewModel = getViewModelFactory().getEditBabysitterViewModel();
        firstName.textProperty().bindBidirectional(viewModel.getFirstName());
        lastName.textProperty().bindBidirectional(viewModel.getLastName());
        username.textProperty().bindBidirectional(viewModel.getUsername());
        Bindings.bindBidirectional(age.valueProperty(), viewModel.getAge());
        Bindings.bindBidirectional(babysittingExperience.textProperty(),
            viewModel.getBabysittingExperience(), new DoubleStringConverter());
        Bindings.bindBidirectional(paymentPerHour.textProperty(),
            viewModel.getPaymentPerHour(), new DoubleStringConverter());
        language1.textProperty().bindBidirectional(viewModel.getLanguage1());
        language2.textProperty().bindBidirectional(viewModel.getLanguage2());
        language3.textProperty().bindBidirectional(viewModel.getLanguage3());
        language4.textProperty().bindBidirectional(viewModel.getLanguage4());
        hasCertificate.selectedProperty()
            .bindBidirectional(viewModel.getHasCertificate());
        errorLabel.textProperty().bind(viewModel.getError());
    }

    @Override public void reset()
    {
        viewModel.reset();
    }

    public void onSave()
    {
        //TODO make a save method!
        getViewHandler().openView(View.BABYSITTER_PROFILE_VIEW);
    }

    public void onCancel()
    {
        getViewHandler().openView(View.BABYSITTER_PROFILE_VIEW);
    }
}
