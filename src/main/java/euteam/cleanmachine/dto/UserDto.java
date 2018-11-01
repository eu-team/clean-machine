package euteam.cleanmachine.dto;

import euteam.cleanmachine.model.enums.Role;
import euteam.cleanmachine.model.user.AuthItem;

import java.util.List;

public class UserDto {
    private Long id;
    private String name;
    private String email;
    private List<AuthItem> authItemList;
    private Role role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<AuthItem> getAuthItemList() {
        return authItemList;
    }

    public void setAuthItemList(List<AuthItem> authItemList) {
        this.authItemList = authItemList;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
