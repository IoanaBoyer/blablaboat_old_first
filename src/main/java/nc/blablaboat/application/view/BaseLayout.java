package nc.blablaboat.application.view;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class BaseLayout extends Composite<Div> {

    private final Image appIcon;
    private final Button myAccountButton;
    private final VerticalLayout content = new VerticalLayout();

    public BaseLayout() {
        appIcon = new Image("images/BlaBlaBoat.png", "blablaboat logo");
        appIcon.addClickListener(event -> getUI().ifPresent(ui -> ui.navigate("/")));

        myAccountButton = new Button("Account");
        myAccountButton.addClickListener(event -> getUI().ifPresent(ui -> ui.navigate("account"))); // Remplacez "mon-compte" par le chemin de la page de gestion de compte

        getContent().add(header(), content);
    }

    private Component header() {
        appIcon.setHeight("70px");
        return new HorizontalLayout(appIcon, myAccountButton);
    }

    public void setContent(Component... components) {
        content.removeAll();
        content.add(components);
    }
}
