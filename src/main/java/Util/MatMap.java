package Util;

import java.util.HashMap;

public class MatMap {
	private static HashMap<String, String> matMap;
	
	public MatMap () {
		matMap = new HashMap<String, String>();
		matMap.put("1", "Chenille Yarn");
		matMap.put("2", "Cotton Yarn");
		matMap.put("3", "Rayon");
		matMap.put("4", "Fancy Yarn");
		matMap.put("5", "Filament Yarn");
		matMap.put("6", "Polyester Yarn");
		matMap.put("7", "Wool Yarn");
		matMap.put("8", "Cashmere");
		matMap.put("9", "Linen Yarn");
		matMap.put("A", "Artificial Cotton");
		matMap.put("B", "100% Cotton");
		matMap.put("C", "Twisted Yarn");
		matMap.put("D", "Poly Cotton");
		matMap.put("E", "Slub Yarn");
		matMap.put("F", "Silk");
		matMap.put("G", "Acrylic Yarn");
		matMap.put("H", "Fire Retardent");
		matMap.put("J", "Filament Polyester");
		matMap.put("K", "Nylon");
		matMap.put("L", "Poly Linen");
		matMap.put("N", "Recycled Yarn");
	}
	
	public String getType(String key) {
		return matMap.get(key);
	}
}