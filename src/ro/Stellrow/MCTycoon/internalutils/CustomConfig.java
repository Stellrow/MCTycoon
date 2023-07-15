package ro.Stellrow.MCTycoon.internalutils;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class CustomConfig {
    private File file;
    private FileConfiguration fileConfiguration;

    public CustomConfig(String name, JavaPlugin main){
        if(!main.getDataFolder().exists()){
            main.getDataFolder().mkdirs();
        }

        file = new File(main.getDataFolder(),name+".yml");
        if(!file.exists()){
            try {
                file.createNewFile();
                if(main.getResource(name+".yml")!=null){
                    main.saveResource(name+".yml",true);
                }
            }catch (Exception exception){
                exception.printStackTrace();
            }
        }
        fileConfiguration = YamlConfiguration.loadConfiguration(file);


    }

    public CustomConfig(String subfolder, String name, JavaPlugin main){
        if(!main.getDataFolder().exists()){
            main.getDataFolder().mkdirs();
        }
        File folder = new File(main.getDataFolder(),subfolder);
        if(!folder.exists()){
            try{
                folder.mkdirs();
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
        file = new File(main.getDataFolder()+"/"+subfolder,name+".yml");
        if(!file.exists()){
            try {
                file.createNewFile();
                if(main.getResource("/"+subfolder+name+".yml")!=null){
                    main.saveResource("/"+subfolder+name+".yml",true);
                }
            }catch (Exception exception){
                exception.printStackTrace();
            }
        }
        fileConfiguration = YamlConfiguration.loadConfiguration(file);


    }

    public FileConfiguration getConfig(){
        return fileConfiguration;
    }

    public void save(){
        try {
            fileConfiguration.save(file);
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }
    public String getString(String path){
        return getConfig().getString(path);
    }
    public void reload(){
        fileConfiguration = YamlConfiguration.loadConfiguration(file);
    }
    public void deleteFile(){
        file.delete();
    }
}

