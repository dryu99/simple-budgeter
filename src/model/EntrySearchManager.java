package model;

import java.util.HashMap;
import java.util.Map;

// TODO: does it make sense to have another entry storage that stores entries thru a diff data structure? in this case the intent is to be able to search for entries more uickly

// CLASS: similarly to EntryManager, stores data except in a map
public class EntrySearchManager {
    private Map<SimpleDate,Entry> entryMap = new HashMap<>();


}
