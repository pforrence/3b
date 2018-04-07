package symboltable;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.Collection;

public class Helper
{
	public static List<String> keysToSortedList(Collection<String> collection)
	{
		List<String> list = new ArrayList<String>(collection);
		
		java.util.Collections.sort(list);
		
		return list;
	}
}
