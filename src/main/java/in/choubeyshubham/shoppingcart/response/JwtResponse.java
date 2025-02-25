package in.choubeyshubham.shoppingcart.response;


import java.util.Objects;

public class JwtResponse {
    private Long id;
    private String token;

    public JwtResponse(Long id, String token) {
        this.id = id;
        this.token = token;
    }

    public JwtResponse() {
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof JwtResponse that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(token, that.token);
    }

    @Override
    public String toString() {
        return "JwtResponse{" +
                "id=" + id +
                ", token='" + token + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, token);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

