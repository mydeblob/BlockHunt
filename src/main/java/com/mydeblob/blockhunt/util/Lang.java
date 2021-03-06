package com.mydeblob.blockhunt.util;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.List;

/**
 * @author Mydeblob
 * @since 2/17/15
 */
public enum Lang {

    /**
     * Misc
     */
    PREFIX("prefix", "&7[&bBlockHunt&7]");


    private String path;
    private String def;
    private static YamlConfiguration lang;

    Lang(String path, String def) {
        this.path = path;
        this.def = def;
    }

    /**
     * Sets the yaml file to get the message from (Usally called in onEnable)
     * @param langConfig - The yamlconfiguration to use
     */
    public static void setFile(YamlConfiguration langConfig) {
        lang = langConfig;
    }

    /**
     * Converts the enum to a string (Not replacing anything, just converting colors)
     * @return String - The formatted string
     */
    public String toString() {
        if (this == PREFIX){
            return ChatColor.translateAlternateColorCodes('&', lang.getString(this.path, def)) + " ";
        }else{
            return ChatColor.translateAlternateColorCodes('&', lang.getString(this.path, def));
        }
    }

    /**
     * Converts the enum to a string replacing what needs to be replaced
     * @param keyword - The keyword to watch to be replaced, i.e %s%
     * @param replacedWith - What the keyword (Must be the same index as the keyword) should be replaced with
     * @return String - The formatted string
     */
    public String toString(List<String> keyword, List<String> replacedWith){
        String s = ChatColor.translateAlternateColorCodes('&', lang.getString(this.path, def));
        for(String key:keyword){
            if(s.toLowerCase().contains(key.toLowerCase())){
                s = s.replaceAll(key, replacedWith.get(keyword.indexOf(key)));
            }
        }
        return s;
    }

}
