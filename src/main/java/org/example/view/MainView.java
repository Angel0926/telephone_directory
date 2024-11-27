package org.example.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import java.util.Arrays;
import java.util.List;

@Route("") // Отображается на главной странице
public class MainView extends VerticalLayout {

    public MainView() {
        // Пример данных для таблицы
        List<Contact> contacts = Arrays.asList(
                new Contact("John Doe", "john.doe@example.com", "1234567890"),
                new Contact("Jane Smith", "jane.smith@example.com", "0987654321")
        );

        // Создаем таблицу (Grid)
        Grid<Contact> grid = new Grid<>(Contact.class);
        grid.setItems(contacts); // Устанавливаем данные
        grid.setColumns("name", "email", "phone"); // Указываем поля для отображения

        add(grid); // Добавляем таблицу на страницу

        Button downloadPdfButton = new Button("Download Contacts PDF", e -> {
            getUI().ifPresent(ui -> ui.getPage().open("/api/reports/contacts/pdf"));
        });

        add(downloadPdfButton);
    }
    // Пример класса Contact
    public static class Contact {
        private String name;
        private String email;
        private String phone;

        public Contact(String name, String email, String phone) {
            this.name = name;
            this.email = email;
            this.phone = phone;
        }

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }

        public String getPhone() {
            return phone;
        }
    }
}
