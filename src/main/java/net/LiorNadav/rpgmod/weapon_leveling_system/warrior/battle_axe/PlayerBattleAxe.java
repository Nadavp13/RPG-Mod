package net.LiorNadav.rpgmod.weapon_leveling_system.warrior.battle_axe;

import net.minecraft.nbt.CompoundTag;

public class PlayerBattleAxe {
    private int axeLevel;
    private int axeExperience;
    private int axeAdvancementFlag; // 0,1,2
    private final int [] axeExperienceRequirement;
    private final int MIN_LEVEL = 0;
    private final int [] MAX_LEVEL = {30,60,100};

    public PlayerBattleAxe(){
        axeExperienceRequirement = new int[100];
        resetExperienceArray(axeExperienceRequirement);
        axeAdvancementFlag = 0;
    }

    private void resetExperienceArray(int[] arr){
        int amount = 10;
        arr[0] = amount;
        for(int i = 1; i < arr.length; i++){
            arr[i] = arr[i - 1] + (int) (amount * (1 + ((double) i / 10)));
        }
    }

    public void addExperience(int add){
        if (axeLevel < MAX_LEVEL[axeAdvancementFlag] && add > 0) {
            if (axeExperience + add >= axeExperienceRequirement[axeLevel]) {
                int remainingAdd = add - (axeExperienceRequirement[axeLevel] - axeExperience);
                addLevel(1);
                addExperience(remainingAdd);
            }
            else{
                axeExperience+=add;
            }
        }
    }

    public void addLevel(int level){
        if (level >= 0) {
            axeLevel = Math.min(axeLevel + level, MAX_LEVEL[axeAdvancementFlag]);
            if (axeLevel >= MAX_LEVEL[axeAdvancementFlag]){
                axeAdvancementFlag = Math.min(2, axeAdvancementFlag + 1);
            }
            axeExperience = 0;
        }
    }

    public void setAxeLevel(int level){
        if (level <= MAX_LEVEL[axeAdvancementFlag] && level >= MIN_LEVEL) {
            axeLevel = level;
            //Optional log output with level successfully set to level.
        }
        else{
            //Log output with wrong level given, levels of knife can be set between 1-10.
        }
    }

    public void setAxeExperience(int experience){
        if(experience >= 0){
            axeExperience = experience;
        }
    }

    public int getAxeExperience() {
        return axeExperience;
    }

    public int getAxeLevel() { return axeLevel; }

    public void copyFrom(PlayerBattleAxe source){
        axeLevel = source.axeLevel;
        axeExperience = source.axeExperience;
        axeAdvancementFlag = source.axeAdvancementFlag;
    }

    public void saveNBTData (CompoundTag nbt){
        nbt.putInt("axeLevel", axeLevel);
        nbt.putInt("axeExperience", axeExperience);
        nbt.putInt("axeAdvancementFlag", axeAdvancementFlag);
    }

    public void loadNBTData (CompoundTag nbt){
        axeLevel = nbt.getInt("axeLevel");
        axeExperience = nbt.getInt("axeExperience");
        axeAdvancementFlag = nbt.getInt("axeAdvancementFlag");
    }
}
