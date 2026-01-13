public class Contact {

    Long id = null;   // We use null to signal "new" record
    String fullname = "";
    String email = "";
    Long accountId = null;

    public Contact() {}

    public Contact(Long id, String fullname, String email, Long accountId) {
        this.id = id;
        this.fullname = fullname;
        this.email = email;
        this.accountId = accountId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", fullname='" + fullname + '\'' +
                ", email='" + email + '\'' +
                ", accountId=" + accountId +
                '}';
    }
}
