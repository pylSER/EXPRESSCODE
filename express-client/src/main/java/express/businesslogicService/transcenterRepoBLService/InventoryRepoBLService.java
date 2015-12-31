package express.businesslogicService.transcenterRepoBLService;

import java.util.ArrayList;

import express.vo.InDocVO;

public interface InventoryRepoBLService {

	public ArrayList<InDocVO> inventoryRepo(String orgID);
	
	public boolean exportExcel(String orgID);
	
	public void endRepoInventory();
}
