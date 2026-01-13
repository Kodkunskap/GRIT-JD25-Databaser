public class Main {

    public static void main(String[] args) {

        ContactsDAO contactsDAO = new ContactsDAO();

        Contact martin = contactsDAO.getContactById(6L);
        if(martin != null) {
            System.out.println(martin);
        }

        /*
        Contact andrei = new Contact();
        andrei.setFullname("Andrei");
        andrei.setEmail("epost@epost.com");
        andrei.setAccountId(10L);
        contactsDAO.saveContact(andrei);
        System.out.println(andrei); // Should now print with the generated ID
        */


        /*
        Contact andrei = contactsDAO.getContactById(10L);
        andrei.setEmail("enannan@epost.se");
        contactsDAO.saveContact(andrei);
        */

    }

}
