package immoprices;
import java.io.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Extract_Data {

	public static void main(String[] args) throws Exception {
		//String location_id="473362"; String location_name="Gamla";
		//String location_id="473460"; String location_name="Satra";
		String location_id="17846",location_name="Lidingo";
		//String location_name="test";
		String url="http://www.hemnet.se/salda/bostader?utf8=✓"+"&location_ids[]="+location_id;
		Document doc = Jsoup.connect(url).get();
		/*
		File input = new File("./test/satra_p4.htm");
		Document doc = Jsoup.parse(input, "UTF-8", "http://www.hemnet.se/salda/bostader?location_ids[]=473460&page=4");
		*/
		
		Elements pages=doc.select("div.pagination").select("a");
		String nb_pages_str=pages.get(pages.size()-2).text();
		int nb_pages=Integer.parseInt(nb_pages_str),k_page;


		// Initialization of the elements
		Elements asked_price_list= new Elements();
		Elements surface_room_list =new Elements();
		Elements address_list = new Elements();
		Elements city_list=new Elements();
		Elements final_price_list =new Elements();
		Elements sold_date_list =new Elements();


		//Loop for each page
		for(k_page=1;k_page<nb_pages+1;k_page++){
			doc = Jsoup.connect(url+"&page="+ Integer.toString(k_page)).get();
			Elements asked_price_to_be_added=new Elements();
			Elements final_price_to_be_added=new Elements();
			Elements sold_date_to_be_added=new Elements();
			Elements address_to_be_added=new Elements();
			Elements city_to_be_added=new Elements();

			Elements house=new Elements();
			Elements houses_to_be_added=doc.select("ul#search-results").get(0).children();
			Elements surface_room_to_be_added= new Elements();
			houses_to_be_added.remove(3);
			houses_to_be_added.remove(5);
			
			Elements to_be_added=doc.select("li.item-result-meta-attribute-is-bold  span.item-link");


			int k_house=0;
			for(k_house=0;k_house< to_be_added.size() / 3;k_house++){//Loop for each house
				//getting all the data from the house
				house=houses_to_be_added.get(k_house).children().get(0).children();
				//System.out.println(house);
				//getting the surface data
				surface_room_to_be_added.add(house.get(4).children().get(0));
				//getting the final price data
				final_price_to_be_added.add(house.get(2).children().get(0));
				//getting the asked price data
				if ((house.get(2).childNodeSize() -3 )/2 >= 3  ){
					asked_price_to_be_added.add(house.get(2).children().get(3));
				}
				else{
					asked_price_to_be_added.add(null);
				}
				//getting the sold date
				sold_date_to_be_added.add(house.get(1).children().get(0));
				//getting the address
				address_to_be_added.add(house.get(3).children().get(0));
				//getting the city
				city_to_be_added.add(house.get(3).children().get(1));

				
			}
			// Validating the data for the page and store them	
			if ((final_price_to_be_added.size()==50 && surface_room_to_be_added.size()==50 && asked_price_to_be_added.size()==50) 
					|| 
					( (k_page==nb_pages ) && final_price_to_be_added.size() == surface_room_to_be_added.size() && surface_room_to_be_added.size()==asked_price_to_be_added.size()))
			{
				asked_price_list.addAll(asked_price_to_be_added);
				surface_room_list.addAll(surface_room_to_be_added);
				city_list.addAll(city_to_be_added);
				final_price_list.addAll(final_price_to_be_added);
				sold_date_list.addAll(sold_date_to_be_added);
				address_list.addAll(address_to_be_added);

			}
			else{
				System.out.println(k_page);
				System.out.println(asked_price_to_be_added.size());
				System.out.println(final_price_to_be_added.size());
				System.out.println(surface_room_to_be_added.size());
				System.out.println(asked_price_to_be_added);
			}

		}

		GenerateCsv csv=new GenerateCsv("./data/"+location_name+".csv",asked_price_list,surface_room_list,address_list,city_list,final_price_list,sold_date_list);


	}
	
	
}
