package blog.forms;

import javax.validation.constraints.Size;

import com.sun.istack.internal.NotNull;

@SuppressWarnings("restriction")
public class LoginForm {

    @Size(min = 2, max = 30,
            message = "el tamaño tiene que estar comprendido entre 2 y 30 caracteres")
    private String username;

    @Size(min = 3)
    @NotNull
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
