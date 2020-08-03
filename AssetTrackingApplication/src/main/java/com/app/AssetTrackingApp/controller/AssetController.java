package com.app.AssetTrackingApp.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
 
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult; 
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.app.AssetTrackingApp.model.Asset;
import com.app.AssetTrackingApp.model.Category;
import com.app.AssetTrackingApp.model.Location;
import com.app.AssetTrackingApp.model.Site;
import com.app.AssetTrackingApp.model.Vendor;
import com.app.AssetTrackingApp.repository.AssetRepository;
import com.app.AssetTrackingApp.repository.CategoryRepository;
import com.app.AssetTrackingApp.repository.LocationRepository;
import com.app.AssetTrackingApp.repository.SiteRepository;
import com.app.AssetTrackingApp.repository.VendorRepository;
import com.app.AssetTrackingApp.services.FileAsset;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

@Controller
@RequestMapping(value = "/")
public class AssetController {
	
	@Autowired
	AssetRepository assetRepository;
	
	@Autowired
	SiteRepository siteRepository;
	
	@Autowired
	VendorRepository vendorRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	LocationRepository locationRepository;
	
	@GetMapping("/assets")
	public ModelAndView allAssets(ModelAndView mv) {
		mv.addObject("all_site_info", siteRepository.getAllSites());
		mv.setViewName("assets");
		return mv;
	}
	
	@GetMapping(value = "/siteassets/{site_id}")
	public ModelAndView listSiteAssets(@PathVariable("site_id") int site_id) {
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("all_site_assets", assetRepository.getAssetsBySiteId(site_id));
		mv.addObject("vendors", getVendorNameById());
		mv.addObject("categories", getCategoryNameById());
		mv.addObject("locations", getLocationNameBySiteId(site_id));
		mv.addObject("this_site", siteRepository.findBySiteId(site_id));
		mv.setViewName("siteassets");
		
		return mv;
	} 
	
	@GetMapping(value = "/newasset/{site_id}")
	public ModelAndView newAssetForm(@PathVariable("site_id") int site_id, ModelAndView mv) {
		mv.addObject("asset", new Asset());
		mv.addObject("all_locationsMap", getLocationNameBySiteId(site_id));
		mv.addObject("vendorsMap", getVendorNameById());
		mv.addObject("categoriesMap", getCategoryNameById());
		mv.addObject("this_site", siteRepository.findBySiteId(site_id));
		mv.setViewName("newasset");

		return mv;
	}
	
	@PostMapping(value = "/newasset/{site_id}")
	public ModelAndView NewAsset(@Valid @ModelAttribute("asset") Asset asset, BindingResult result, @PathVariable("site_id") int site_id) {
		 
		ModelAndView mv = new ModelAndView();
		
//		if(asset.getAsset_name().isBlank()) {
//			System.out.println("blank");
//			result.addError(new FieldError("asset", "asset_name", "Asset name cannot be blank!"));
//		}
		
		if(result.hasErrors()) {
			System.out.println("there are errors"); 
			mv.addObject("sitesMap", getSiteNameById());
			mv.addObject("vendorsMap", getVendorNameById());
			mv.addObject("categoriesMap", getCategoryNameById()); 
			mv.addObject("site_locations_map", siteLocMap());
			mv.addObject("all_locationsMap", getLocationNameBySiteId(site_id));
			mv.addObject("this_site", siteRepository.findBySiteId(site_id));
			mv.setViewName("newasset");
			return mv;     
		} 
		assetRepository.addNewAsset(asset); 
		return new ModelAndView("redirect:/siteassets/" + asset.getLocation_site_id());
	}

	//----------UPDATE ASSET -----------------
	
	@GetMapping(value = "/updateasset/{asset_id}")
	public ModelAndView updateAssetForm(@PathVariable("asset_id") int asset_id, ModelAndView mv) {
		Asset asset = assetRepository.findAssetById(asset_id);
		mv.addObject("this_asset", asset);
		mv.addObject("all_locationsMap", getLocationNameBySiteId(asset.getLocation_site_id()));
		mv.addObject("vendorsMap", getVendorNameById());
		mv.addObject("categoriesMap", getCategoryNameById());
		mv.addObject("this_site", siteRepository.findBySiteId(asset.getLocation_site_id()));
		mv.setViewName("updateasset");

		return mv;
	}
	
	@PostMapping(value = "/updateasset/{asset_id}")
	public ModelAndView updateAsset(@Valid @ModelAttribute("this_asset") Asset asset, BindingResult result, @PathVariable("asset_id") int asset_id) {
		 
		ModelAndView mv = new ModelAndView();
		
		if(result.hasErrors()) { 
			mv.addObject("sitesMap", getSiteNameById());
			mv.addObject("vendorsMap", getVendorNameById());
			mv.addObject("categoriesMap", getCategoryNameById()); 
			mv.addObject("site_locations_map", siteLocMap());
			mv.addObject("all_locationsMap", getLocationNameBySiteId(asset.getLocation_site_id()));
			mv.addObject("this_site", siteRepository.findBySiteId(asset.getLocation_site_id()));
			mv.setViewName("updateasset");
			return mv;     
		} 
		assetRepository.updateAsset(asset); 
		return new ModelAndView("redirect:/siteassets/" + asset.getLocation_site_id());
	}
	
	
	//---------------------------------------------------
	//---------------UPLOAD EXCEL FILE-------------------
	
	@GetMapping("/uploadExcelFile")
    public ModelAndView uploadFileForm() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("sitesMap", getSiteNameById());
		mv.addObject("categoriesMap", getCategoryNameById());
		mv.setViewName("uploadassetdata");
        return mv;
    }
    
	@PostMapping("/uploadExcelFile")
	public ModelAndView uploadFile(Model model, MultipartFile file, @RequestParam("this_site") int siteid, @RequestParam("this_category") int categoryid) throws IOException {
	   
	    XSSFWorkbook asset_workbook  = new XSSFWorkbook(file.getInputStream());	
 
	    model.addAttribute("message", "File: " + file.getOriginalFilename() 
	      + " has been uploaded successfully!");
	    processFile(asset_workbook, siteid, categoryid); 
		
		return new ModelAndView("redirect:/siteassets/"+ siteid);
	}
	
	//---------------QR CODE-------------------------
	
	@GetMapping(value = "/getqrcode/{asset_id}")
	public ModelAndView getQRCode(@PathVariable("asset_id") int asset_id, HttpServletResponse response) throws WriterException, IOException {
		
		Asset asset = assetRepository.findAssetById(asset_id);
		String assetINFO = asset.toString();
		
		String vendorName = getVendorNameById().get(asset.getVendor_id());
		String categoryName = getCategoryNameById().get(asset.getCategory_id());
		String locationName = getLocationNameById().get(asset.getLocation_id());
		String siteName = getSiteNameById().get(asset.getLocation_site_id());
		
		String toQRCODE = assetINFO + "\n |VENDOR: " + vendorName + "|\n |CATEGORY: " + categoryName + "|\n |LOCATION:" + locationName + "|\n |SITE:" + siteName + "|";
		System.out.println(toQRCODE);
			 
		byte[] QR = getQRCodeImage(toQRCODE, 150, 150);
		String base64Encoded = Base64.getEncoder().encodeToString(QR); 
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("asset", asset);
		mv.addObject("vendorName", vendorName);
		mv.addObject("categoryName", categoryName);
		mv.addObject("locationName", locationName);
		mv.addObject("siteName", siteName);
		
		mv.setViewName("QRCODE");		
		mv.addObject("QR_CODE", base64Encoded);
		
		return mv;
	}
 
	private byte[] getQRCodeImage(String text, int width, int height) throws WriterException, IOException {
	    QRCodeWriter qrCodeWriter = new QRCodeWriter();
	    BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
	    
	    ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
	    MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
	    byte[] pngData = pngOutputStream.toByteArray(); 
	    return pngData;
	}
 
	
	@GetMapping(value = "/deleteasset")
	public ModelAndView deleteAsset(@RequestParam("asset_id") long asset_id) { 
		Site site = assetRepository.getSiteByAssetId(asset_id);
		assetRepository.deleteAsset(asset_id);		
		return new ModelAndView("redirect:/siteassets/"+ site.getSite_id());
	}
	
	@GetMapping(value = "/locationassets/{location_id}")
	public ModelAndView getLocationAssets(@PathVariable("location_id") int location_id, ModelAndView mv) {
		mv.addObject("location_assets", assetRepository.getAssetsByLocationId(location_id));
		mv.addObject("vendors", getVendorNameById());
		mv.addObject("categories", getCategoryNameById());
		mv.addObject("this_site", locationRepository.getSiteByLocationId(location_id));
		mv.addObject("this_location", locationRepository.findLocationById(location_id));
		mv.setViewName("locationassets");
		return mv;
	}

	@GetMapping(value = "/categoryassets/{category_id}")
	public ModelAndView listAssetsByCategory(@PathVariable("category_id") int category_id, ModelAndView mv) {
		mv.addObject("cassets", assetRepository.getAssetsByCategoryId(category_id));
		mv.addObject("vendors", getVendorNameById());
		mv.addObject("categories", getCategoryNameById());
		mv.addObject("locations", getLocationNameById());
		mv.addObject("sites", getSiteNameById());
		mv.addObject("this_category", categoryRepository.findCategoryById(category_id));
		mv.setViewName("categoryassets");
		return mv;
	}
	
    //----------------PROCESS FILE-----------------------------
	private void processFile(XSSFWorkbook workbook, int site_id, int category_id) {

		if(verifyWorksheet(workbook.getSheetAt(0))) {
			
		processWorksheet(workbook.getSheetAt(0), site_id, category_id);	
		System.out.println("Worksheet " + workbook.getSheetName(0) + " is being processed!");
		
		}
		else {
			System.out.println("Worksheet " + workbook.getSheetName(0) + " could not be verified!");
		}
		
	}
	
	private boolean verifyWorksheet(Sheet sheet) {
		
		Row firstRow = sheet.getRow(0);
		if( firstRow.getCell(0) == null ||
			firstRow.getCell(1) == null ||
			firstRow.getCell(2) == null ||
			firstRow.getCell(3) == null ||
			firstRow.getCell(4) == null ||
			firstRow.getCell(5) == null ||
			firstRow.getCell(6) == null ||
			firstRow.getCell(7) == null ) {
			return false;
		}
		
		if(firstRow.getCell(0).toString().equalsIgnoreCase("Asset Name") &&
		   firstRow.getCell(1).toString().equalsIgnoreCase("Description") &&
		   firstRow.getCell(2).toString().equalsIgnoreCase("Asset Tag") &&
		   firstRow.getCell(3).toString().equalsIgnoreCase("PO Number") &&
		   firstRow.getCell(4).toString().equalsIgnoreCase("Quantity") &&
		   firstRow.getCell(5).toString().equalsIgnoreCase("Purchase Date") &&
		   firstRow.getCell(6).toString().equalsIgnoreCase("Purchase Cost") &&
		   firstRow.getCell(7).toString().equalsIgnoreCase("Vendor") ) {
		   System.out.println(" Verified!");
		   return true;	
		}	
		return false;
	}
	
	private void processWorksheet(Sheet sheet, int siteid, int categoryid){

		DataFormatter formatter = new DataFormatter();
		ArrayList<FileAsset> fileAssetList = new ArrayList<FileAsset>();
		HashMap<String, Set<String>> partsToCreate = new HashMap<>();
		
		Set<String> vendors = new HashSet<String>();	
		
        for(Row row : sheet) {
        	//System.out.println("\n#####" + row.getRowNum());
        		
    		if(row.getRowNum() == 0 || row == null || row.getCell(0) == null || row.getCell(1) == null) {
    			continue;//header, empty
    		}
    		
    		if(isRowEmpty(row)) {
    			continue;
    		}
    		
    		FileAsset asset = new FileAsset();		
    		ArrayList<String> rowContent = new ArrayList<String>();
    		
    		for(Cell cell : row) {
    			String cellVal = formatter.formatCellValue(cell);
    			if(cellVal == null || cellVal == "") {
    				cellVal = "";
    			}
    			rowContent.add(cellVal);
    		}
    		
    		String assetname = rowContent.get(0);
    		String description = rowContent.get(1);
    		String assetTag = rowContent.get(2);
    		String poNumber = rowContent.get(3);
    		String quantity = rowContent.get(4);
    		String purchaseDate = rowContent.get(5); 
    		String purchaseCost = rowContent.get(6);
    		String vendor = rowContent.get(7);
       		
    		asset.setAsset_name(assetname);
    		asset.setAsset_description(description);
    		asset.setAsset_tag(assetTag);
    		asset.setPo_number(poNumber);
    		asset.setQuantity(parseInt(quantity));
    		asset.setPurchase_date(parseDate(purchaseDate));
    		asset.setPurchase_cost(parseDouble(purchaseCost.replaceAll("[,]", "")));
    		    		
    		asset.setCategoryid(categoryid);
    		
    		asset.setSiteid(siteid);
    		asset.setLocationid(locationRepository.getTempLocationBySiteId(siteid).getLocation_id());
    		asset.setVendorName(vendor);
    		vendors.add(vendor);

    		System.out.println(asset.toString());
    		fileAssetList.add(asset);
    }
        partsToCreate.put("vendor", vendors);
        createRequiredParts(partsToCreate);
        handleFileAssets(fileAssetList);
	}

	private static boolean isRowEmpty(Row row) {
		boolean isEmpty = true;
		DataFormatter dataFormatter = new DataFormatter();

		if (row != null) {
			for (Cell cell : row) {
				if (dataFormatter.formatCellValue(cell).trim().length() > 0) {
					isEmpty = false;
					break;
				}
			}
		}

		return isEmpty;
	}
	
	private String parseDate(String date_old){
		String OLD_FORMAT = "dd/MM/yyyy";
		String NEW_FORMAT = "yyyy-MM-dd";
		String date_new;
		
		
		SimpleDateFormat format = new SimpleDateFormat(OLD_FORMAT);
		Date date;
		try {
			date = format.parse(date_old);
			format.applyPattern(NEW_FORMAT);		
			date_new = format.format(date);
		    return date_new;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "2020-01-01";
		}

	}
	
	double parseDouble(String strNumber) {
		
		   if (strNumber != null && strNumber.length() > 0) {
		       try {
		          
		    	    return Double.parseDouble(strNumber);
		       }
		       catch(Exception e) {
		            
		    	    return 0; 
		       }
		   }
		   else return 0;
	}

	int parseInt(String strNumber) {
		
		   if (strNumber != null && strNumber.length() > 0) {
		       try {
		          
		    	    return Integer.parseInt(strNumber);
		       }
		       catch(Exception e) {
		            
		    	    return 1; 
		       }
		   }
		   else return 1;
	}
	
	private void createRequiredParts(HashMap<String, Set<String> > requiredParts) {
		System.out.println("here");
		//Check Vendors
		for(String vendorName : requiredParts.get("vendor")) {
			
			if(getVendorNameById().values().stream().anyMatch(vendorName::equalsIgnoreCase)) {
				System.out.println(">>VENDOR: " + vendorName + " exists!");
			}
			else {
				Vendor vendor = new Vendor();
				vendor.setVendor_name(vendorName);
				vendorRepository.addNewVendor(vendor);
				System.out.println(">>VENDOR: " + vendorName + " created!");
			}
		}

		//Check Locations
//		for(String locationName : requiredParts.get("location")) {
//			
//			if(getLocationNameBySiteId(siteid).values().stream().anyMatch(locationName::equalsIgnoreCase)) {
//				System.out.println(">>LOCATION " + locationName + " exists!");
//			}
//			else {
//				Location location = new Location();
//				location.setLocation_name(locationName);
//				location.setLocation_siteId(siteid);
//				locationRepository.addLocation(location);
//				System.out.println(">>LOCATION " + locationName + " created!");
//			}
//		}
	}
	
	private void handleFileAssets(ArrayList<FileAsset> fileAssetList) {
		for(FileAsset f : fileAssetList) {
			Asset asset = new Asset();
			asset.setAsset_name(f.getAsset_name());
			asset.setAsset_description(f.getAsset_description());
			asset.setAsset_tag(f.getAsset_tag());
			asset.setPo_number(f.getPo_number());
			asset.setQuantity(f.getQuantity());
			asset.setPurchase_date(f.getPurchase_date());
			asset.setPurchase_cost(f.getPurchase_cost());
			asset.setSalvage_value(0);
			asset.setIs_lost(false);
			asset.setIs_broken(false);
			asset.setVendor_id(vendorRepository.getVendorByVendorName(f.getVendorName()).getVendor_id());
			asset.setCategory_id(f.getCategoryid());
			asset.setLocation_site_id(f.getSiteid());
			asset.setLocation_id(locationRepository.getTempLocationBySiteId(f.getSiteid()).getLocation_id());
			
			assetRepository.addNewAsset(asset);
		}
	}


	
	private HashMap<Integer, String> getSiteNameById(){
		HashMap<Integer, String> site_id_list = new HashMap<>();
		for(Site site : siteRepository.getAllSites()) {
			site_id_list.put(site.getSite_id(), site.getSite_name());
		}
		return site_id_list;
	}
	
	private HashMap<Integer, String> getVendorNameById(){
		HashMap<Integer, String> vendor_id_list = new HashMap<>();
		for(Vendor vendor : vendorRepository.getAllVendors()) {
			vendor_id_list.put(vendor.getVendor_id(), vendor.getVendor_name());
		}
		return vendor_id_list;
	}
	
	private HashMap<Integer, String> getCategoryNameById(){
		HashMap<Integer, String> category_id_list = new HashMap<>();
		for(Category category : categoryRepository.getAllCategories()) {
			category_id_list.put(category.getCategory_id(), category.getCategory_name());
		}
		return category_id_list;
	}
	
	private HashMap<Integer, String> getLocationNameBySiteId(int site_id){
		HashMap<Integer, String> location_id_list = new HashMap<>();
		for(Location location : locationRepository.getLocationsBySiteId(site_id)) {
			location_id_list.put(location.getLocation_id(), location.getLocation_name());
		}
		return location_id_list;
	}
	
	private HashMap<Integer, String> getLocationNameById(){
		HashMap<Integer, String> location_id_list = new HashMap<>();
		for(Location location : locationRepository.getAllLocations()) {
			location_id_list.put(location.getLocation_id(), location.getLocation_name());
		}
		return location_id_list;
	}
	
	private HashMap<Integer, ArrayList<String>> siteLocationNameMap(){
		HashMap<Integer, ArrayList<String>> siteLocations = new HashMap<Integer, ArrayList<String>>();
		List<Integer> siteIDlist = siteRepository.getSiteIDs();
		for(Integer id : siteIDlist) {
			siteLocations.put(id, locationRepository.getLocationNameBySiteId(id));
		}
		
		return siteLocations;
	}
	
	private HashMap<Integer, HashMap<Integer, String>> siteLocMap(){
		HashMap<Integer, HashMap<Integer, String>> siteLoc= new HashMap<Integer, HashMap<Integer, String>>();
		List<Integer> siteIDlist = siteRepository.getSiteIDs();
		for(Integer id : siteIDlist) {
			siteLoc.put(id, getLocationNameBySiteId(id));
		}
		
		return siteLoc;
	}
}
