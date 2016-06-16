package immoprices;
import java.io.FileWriter;
import java.io.IOException;

import org.jsoup.select.Elements;

public class GenerateCsv {

   
   public GenerateCsv(String sFilename,Elements asked_price_list,
		   Elements surface_room_list,Elements address_list,Elements city_list,Elements final_price_list,
		   Elements sold_date_list)
   {
	   generateCsvFile(sFilename,asked_price_list,surface_room_list,address_list,city_list,final_price_list,sold_date_list); 
   }   
   
   private static void generateCsvFile(String sFilename,Elements asked_price_list,
		   Elements surface_room_list,Elements address_list,Elements city_list,Elements final_price_list,
		   Elements sold_date_list)
   {
	try
	{
	    FileWriter writer = new FileWriter(sFilename);
	    
	    writer.append("AskedPrice");
	    writer.append(',');
	    writer.append("FinalPrice");
	    writer.append(',');
	    writer.append("Surface");
	    writer.append(',');	    
	    writer.append("Number_room");
	    writer.append(',');
	    writer.append("Year");
	    writer.append(',');
	    writer.append("Month");
	    writer.append(',');
	    writer.append("Day");
	    /*writer.append(',');
	    writer.append("Address");
	    writer.append(',');
	    writer.append("Area");
	    writer.append(',');*/
	    writer.append('\n');    
	    int n=asked_price_list.size();
	
		int i;
		for(i=0;i<n;i++){
			String asked_price_real="NaN";
			if (asked_price_list.get(i)!=null){
				String asked_price_str=asked_price_list.get(i).text();
				asked_price_str=asked_price_str.substring(13, asked_price_str.length() -3).replace(" ", "");
				asked_price_real="";
				for (char ch: asked_price_str.toCharArray()) {
					if (ch>='0' && ch<= '9') asked_price_real+=ch;
				}
			}
			
			String final_price_str = final_price_list.get(i).text();
			
			//sort the data
			final_price_str=final_price_str.substring(9, final_price_str.length() -3).replace(" ", "");


			writer.append(asked_price_real);
		    writer.append(',');
		    
			String final_price_real="";

			for (char ch: final_price_str.toCharArray()) {
				if (ch>='0' && ch<= '9') final_price_real+=ch;
			}
		    writer.append(final_price_real);
		    writer.append(',');
		    //Writing surface of the flat
		    int k=0;
			String surface_room_str = surface_room_list.get(i).text();
		    surface_room_str=surface_room_str.replace(',', '.');
		    while ( ( surface_room_str.charAt(k) >= '0' && surface_room_str.charAt(k)  <= '9') || surface_room_str.charAt(k)=='.' ) {
		    			k++;
		    }
		    if (k>0) {
		    	
		    	writer.append(surface_room_str.substring(0, k));
		    }
		    else{
		    	writer.append("NaN");
		    }
		    writer.append(',');
		    //Writing number of rooms
			String room_number_str = surface_room_list.get(i).text();
			if (room_number_str.contains("rum")){
				if (k>0){
					room_number_str=room_number_str.substring(k+5, room_number_str.length()-4).replaceAll("\\s+","").replace(',', '.');
				}
				else{
					room_number_str=room_number_str.substring(1, room_number_str.length()-4).replace(',', '.').replaceAll("\\s+","");
				}
			}
			else {
		    	room_number_str="NaN";
			}		    
			writer.append(room_number_str+',');
		    
		    //Writing sold date
		    String sold_date_str=sold_date_list.get(i).text();
			sold_date_str=sold_date_str.substring(5, sold_date_str.length());
			int k_date=0;
			for (String p : sold_date_str.split("-")){
			    writer.append(p);
			    if (k_date<2){writer.append(',');}
			    k_date++;
			}
			
			//Writing address
			/*String address_str=address_list.get(i).text().replaceAll(",", " ");
		    writer.append(address_str+',');
			//Writing city
		    String city_str=city_list.get(i).text().replaceAll(",", " ");
		    writer.append(city_str+',');
		    */
		 	writer.append('\n');
		    
		}

	    writer.flush();
	    writer.close();
	}
	catch(IOException e)
	{
	     e.printStackTrace();
	} 
    }
}

