package com.nus.adqs.dataaccess.model.master;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.nus.adqs.constant.ConstantSchema;
import com.nus.adqs.dataaccess.model.base.BaseEntity;
import com.nus.adqs.util.StringUtil;


@SuppressWarnings("serial")
@Entity
@NamedQueries(
	@NamedQuery(name="MenuMaster_ListByRootCode",query="SELECT e FROM MenuMaster e WHERE e.code=:code ORDER BY e.seqNo ASC ")
)
@Table(name="MENU_MASTER",schema=ConstantSchema.COMMON)
public class MenuMaster extends BaseEntity{
	
	public static final String MENU_TYPE_GROUP = "G";
	public static final String MENU_TYPE_ITEM = "I";
	
	public static final String LIST_BY_ROOT_CODE = "MenuMaster_ListByRootCode";
	
	@Id
	@Column(name = "CODE",length=10)
	private String code;
	public String getCode() {return StringUtil.idTrim(code);}
	public void setCode(String code) {this.code = StringUtil.idTrim(code);}
	@Override
	public Serializable getPk() {return getCode();}
	
	
	@Column(name = "DESCRIPTION")
	private String description;
	public String getDescription() {return description;}
	public void setDescription(String description) {this.description = description;}
	
	@Column(name = "URL_PATH")
	private String urlPath;
	public String getUrlPath() {return urlPath;}
	public void setUrlPath(String urlPath) {this.urlPath = urlPath;}
	
	@Column(name = "MENU_TYPE",columnDefinition="CHAR(1)",length=1,nullable=false)
	private String menuType;
	public String getMenuType() {return menuType;}
	public void setMenuType(String menuType) {this.menuType = menuType;}
	
	@Column(name="SEQ_NO")
	private Integer seqNo;
	public Integer getSeqNo() {return seqNo;}
	public void setSeqNo(Integer seqNo) {this.seqNo = seqNo;}

	@ManyToOne
	@JoinColumn(name="PARENT_MENU_CODE", referencedColumnName="CODE")
	private MenuMaster parentMenu;
	public MenuMaster getParentMenu() {return parentMenu;}
	public void setParentMenu(MenuMaster parentMenu) {this.parentMenu = parentMenu;}
	
	@OneToMany(mappedBy="parentMenu", fetch=FetchType.EAGER)
	@OrderBy(" seqNo ASC ")
	private List<MenuMaster> subMenus = new ArrayList<MenuMaster>();
	public List<MenuMaster> getSubMenus() {return subMenus;}
	public void setSubMenus(List<MenuMaster> subMenus) {this.subMenus = subMenus;}
	public boolean hasChildMenu(){return (this.isTypeGroup() && !this.subMenus.isEmpty());}
	
	@Transient
	private int menuLevel=0;
	public int getMenuLevel() {return menuLevel;}
	public void setMenuLevel(int menuLevel) {this.menuLevel = menuLevel;}
	
	/***************************************************************UTILITY******************************************************************************/
	
	public boolean isTypeGroup(){return MENU_TYPE_GROUP.equals(this.getMenuType());}
	public boolean isTypeItem(){return MENU_TYPE_ITEM.equals(this.getMenuType());}

}
