package euteam.cleanmachine.dto;

import euteam.cleanmachine.model.enums.RoleName;
import euteam.cleanmachine.model.user.AuthItem;
import euteam.cleanmachine.model.user.NFCCard;

import java.util.List;

public class UserDto {
    private Long id;
    private String name;
    private String email;
    private List<AuthItem> authItemList;
    private RoleName roleName;
    

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

    public RoleName getRoleName() {
        return roleName;
    }

    public void setRoleName(RoleName roleName) {
        this.roleName = roleName;
    }

}
