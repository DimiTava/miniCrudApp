package com.miniCRUD;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;
import java.util.List;

/**
 * This UI is the application entry point. A UI may either represent a browser window
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {

    private CustomerService service = CustomerService.getInstance();
    private Grid grid = new Grid();
    private TextField filterText = new TextField();
    private CustomerForm form=new CustomerForm(this);


    @Override
    protected void init(VaadinRequest vaadinRequest) {

        final VerticalLayout layout = new VerticalLayout();


        filterText.setInputPrompt("Type something...");

        filterText.addTextChangeListener(event -> {
            grid.setContainerDataSource(new BeanItemContainer<>(Customer.class,service.findAll(event.getText())));
        });

        Button btnCFilter=new Button(FontAwesome.RECYCLE);


        Button btnAddCustomer=new Button(FontAwesome.PLUS);

        btnAddCustomer.addClickListener(event -> {
            grid.select(null);
            form.setCustomer(new Customer());
        });



        btnCFilter.addClickListener(event -> {
            filterText.clear();
            updateList();
        });



        HorizontalLayout toolbar= new HorizontalLayout();
        VerticalLayout main=new VerticalLayout(toolbar,grid);

        HorizontalLayout filterArea=new HorizontalLayout();
        filterArea.addComponents(filterText,btnCFilter);
        filterArea.setSpacing(true);

        toolbar.addComponents(filterArea,btnAddCustomer);

        main.setSpacing(true);
        main.setSizeFull();
        grid.setSizeFull();
        main.setExpandRatio(grid,1);

        main.setSizeFull();
//        CssLayout filtering = new CssLayout();
//        filtering.addComponents(filterText, btnCFilter);
//        filtering.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
        toolbar.setSpacing(true);

        grid.setColumns("firstName","lastName","status","email","birthDate");
        layout.addComponents(main,form);



        updateList();


        layout.setMargin(true);
        layout.setSpacing(true);

        setContent(layout);
        form.setVisible(false);
        grid.addSelectionListener(event -> {
            if(event.getSelected().isEmpty()){
                form.setVisible(false);
            }else {
                Customer customer=(Customer) event.getSelected().iterator().next();
                form.setCustomer(customer);
            }

        });
    }

    public void updateList() {
        List<Customer> customers= service.findAll(filterText.getValue());
        grid.setContainerDataSource(new BeanItemContainer<>(Customer.class,customers));
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {


    }
}




