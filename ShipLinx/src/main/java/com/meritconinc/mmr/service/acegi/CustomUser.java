package com.meritconinc.mmr.service.acegi;

import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.userdetails.User;

/**
 *
 * ACEGI wrapper for MMR User 
 */
public class CustomUser extends User {
	public static final long serialVersionUID = 18102007;

    private Object userInfo;
    
    /**
     * Public constructor for CustomUser
     * 
     * @param username
     * @param password
     * @param isEnabled
     * @param accountNonExpired
     * @param credentialsNonExpired
     * @param accountNonLocked
     * @param authorities
     * @param u
     */
    public CustomUser(String username, String password, boolean isEnabled,
    		boolean accountNonExpired, boolean credentialsNonExpired,
    		boolean accountNonLocked,
            GrantedAuthority[] authorities, Object u) {
        super(username, password, isEnabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.setUserInfo(u);
    } 

    /**
     * Public constructor for CustomUser
     * 
     * @param username
     * @param password
     * @param isEnabled
     * @param arrayAuths
     */
    public CustomUser(String username, String password, boolean isEnabled, GrantedAuthority[] arrayAuths) {
        super(username, password, isEnabled, true, true, true, arrayAuths);
    }
   
    /**
     * Returns the User details
     * 
     * @return
     */
    public Object getUserInfo() {
        return userInfo;
    }
    
    /**
     * Sets the user details
     * 
     * @param userInfo
     */
    public void setUserInfo(Object userInfo) {
        this.userInfo = userInfo;
    }
}
