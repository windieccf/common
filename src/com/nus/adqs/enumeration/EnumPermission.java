package com.nus.adqs.enumeration;

public enum EnumPermission {
	CREATE("C", "Create"),
	READ("R", "Read"),
	UPDATE("U", "Update"),
	DELETE("D", "Delete"),
	INVOKE("I", "Invoke");
	
	private String code;
	public String getCode() {return code;}
	
	private String description;
	public String getDescription() {return description;}
	public String getDescriptionLowerCase() {return description.toLowerCase();}

	EnumPermission(String code, String description){
		this.code = code;
		this.description = description;
	}
	
	public static EnumPermission getByCode(String code){
		for(EnumPermission permission : EnumPermission.values()){
			if(permission.getCode().equals(code))
				return permission;
		}
		return null;
	}

}
