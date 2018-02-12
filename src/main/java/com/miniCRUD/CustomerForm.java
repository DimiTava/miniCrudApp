package com.miniCRUD;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.event.ShortcutAction;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

public class CustomerForm extends FormLayout {

    private TextField firstName = new TextField("First Name");
    private TextField lastName = new TextField("Last Name");



    private TextField email = new TextField("Email");
    private NativeSelect status = new NativeSelect("Status");
    private PopupDateField birthDate = new PopupDateField("Birth Date");

    private Button btnSave = new Button("Save");
    private Button btnDelete = new Button("Delete");

    private CustomerService service = CustomerService.getInstance();
    private MyUI myUI;
    private Customer customer;



    public CustomerForm(MyUI myUI) {
        this.myUI = myUI;
        setSizeUndefined();

        firstName.setColumns(20);
        lastName.setColumns(20);
        email.setColumns(20);
        HorizontalLayout buttonArea = new HorizontalLayout(btnSave, btnDelete);
        buttonArea.setSpacing(true);

        status.addItems(CustomerStatus.values());

        btnSave.setStyleName(ValoTheme.BUTTON_PRIMARY);
        btnSave.setClickShortcut(ShortcutAction.KeyCode.ENTER);

        btnSave.addClickListener(e -> this.BtnSave());
        btnDelete.addClickListener(e -> this.BtnDelete());

        addComponents(firstName, lastName, email, status, birthDate, buttonArea);


    }



    public void setCustomer(Customer customer) {

        this.customer = customer;

        BeanFieldGroup.bindFieldsUnbuffered(customer, this);

        btnDelete.setVisible(customer.IsPersisted());
        setVisible(true);
        firstName.selectAll();

    }

    private void BtnSave() {
        if ( customer.getFirstName().isEmpty() ){
            Notification.show("Customer is null",
                    Notification.Type.WARNING_MESSAGE);
            return;

    }
        service.save(customer);
        myUI.updateList();
        setVisible(false);
    }
    private void BtnDelete(){
        service.delete(customer);
        myUI.updateList();
        setVisible(true);
    }
}
