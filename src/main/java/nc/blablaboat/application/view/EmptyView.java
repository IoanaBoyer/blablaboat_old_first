package nc.blablaboat.application.view;

import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;

@PageTitle("Empty")
@Route(value = "/empty")
public class EmptyView extends VerticalLayout {

    public EmptyView() {
        setSpacing(false);

        Image img = new Image("images/BlaBlaBoat.png", "blablaboat logo");
        img.setWidth("200px");
        add(img);

        H2 header = new H2("Let's do it");
        header.addClassNames(Margin.Top.XLARGE, Margin.Bottom.MEDIUM);
        add(header);
        add(new Paragraph("Yoyaa !! 🤗"));

        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("text-align", "center");
    }

}
