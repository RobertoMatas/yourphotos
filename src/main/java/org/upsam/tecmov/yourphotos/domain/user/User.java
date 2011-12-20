package org.upsam.tecmov.yourphotos.domain.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;

@Entity
@Table(name = "USERS")
@SecondaryTable(name = User.AUTHORITIES_TABLE_NAME, pkJoinColumns = @PrimaryKeyJoinColumn(name = "userName"))
public class User {

	protected static final String AUTHORITIES_TABLE_NAME = "AUTHORITIES";

	@Id
	private String userName;

	private String password;

	private Boolean enabled;

	@Enumerated(EnumType.STRING)
	@Column(table = AUTHORITIES_TABLE_NAME)
	private Role authority;

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the enabled
	 */
	public Boolean getEnabled() {
		return enabled;
	}

	/**
	 * @param enabled
	 *            the enabled to set
	 */
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * @return the authority
	 */
	public Role getAuthority() {
		return authority;
	}

	/**
	 * @param authority
	 *            the authority to set
	 */
	public void setAuthority(Role authority) {
		this.authority = authority;
	}

}
