package me.cworldstar.sfdrugs.utils;

import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import me.cworldstar.sfdrugs.SFDrugs;
import me.cworldstar.sfdrugs.implementations.TradingRecipe;
import me.cworldstar.sfdrugs.implementations.traders.CorporationTrader;
import me.cworldstar.sfdrugs.implementations.traders.HookerZombie;
import me.cworldstar.sfdrugs.implementations.traders.MysteriousTrader;

public class Trading {
    private final SFDrugs plugin;
    public HookerZombie Hooker;
    public MysteriousTrader Trader;
    public CorporationTrader Corporate;

    public Trading(SFDrugs sfDrugs) {
        this.plugin = sfDrugs;
    }

    public Trading register() {

        this.Hooker = new HookerZombie();
        this.Trader = new MysteriousTrader();
        this.Corporate = new CorporationTrader();

        TradingRecipe BatonBlueprintRecipe = new TradingRecipe(Items.MONEY, 21, Items.BATON_BLUEPRINT, 1);
        Trader.RegisterTradingRecipe(BatonBlueprintRecipe);
        TradingRecipe ShovelBlueprintRecipe = new TradingRecipe(Items.MONEY, 24, Items.SHOVEL_BLUEPRINT, 1);
        Trader.RegisterTradingRecipe(ShovelBlueprintRecipe);
        TradingRecipe AxeBlueprintRecipe = new TradingRecipe(Items.MONEY, 32, Items.AXE_BLUEPRINT, 1);
        Trader.RegisterTradingRecipe(AxeBlueprintRecipe);
        TradingRecipe PickaxeBlueprintRecipe = new TradingRecipe(Items.MONEY, 42, Items.PICKAXE_BLUEPRINT, 1);

        TradingRecipe DrugHelmetRecipe = new TradingRecipe(Items.MONEY, 22, Items.DRUG_HELMET_BLUEPRINT, 1);
        Trader.RegisterTradingRecipe(DrugHelmetRecipe);
        TradingRecipe DrugChestplateRecipe = new TradingRecipe(Items.MONEY, 24, Items.DRUG_CHESTPLATE_BLUEPRINT, 1);
        Trader.RegisterTradingRecipe(DrugChestplateRecipe);
        TradingRecipe DrugLeggingsRecipe = new TradingRecipe(Items.MONEY, 18, Items.DRUG_LEGGINGS_BLUEPRINT, 1);
        Trader.RegisterTradingRecipe(DrugLeggingsRecipe);
        TradingRecipe DrugBootsRecipe = new TradingRecipe(Items.MONEY, 16, Items.DRUG_BOOTS_BLUEPRINT, 1);
        Trader.RegisterTradingRecipe(DrugBootsRecipe);

        Trader.RegisterTradingRecipe(PickaxeBlueprintRecipe);
        TradingRecipe GStringRecipe = new TradingRecipe(Items.METH, 64, Items.GAY_PANTS, 1);
        Hooker.RegisterTradingRecipe(GStringRecipe);
        TradingRecipe DrugPipeRecipe = new TradingRecipe(Items.METH, 10, Items.DRUG_PIPE, 1);
        Hooker.RegisterTradingRecipe(DrugPipeRecipe);
        TradingRecipe SnaidsRecipe = new TradingRecipe(Items.METH, 30, Items.SNAIDS, 1);
        Hooker.RegisterTradingRecipe(SnaidsRecipe);
        TradingRecipe CircuitBoard = new TradingRecipe(Items.MONEY, 1, SlimefunItems.BASIC_CIRCUIT_BOARD, 64);
        Trader.RegisterTradingRecipe(CircuitBoard);
        TradingRecipe CorporationMoneyStampRecipe = new TradingRecipe(Items.MONEY, 30, Items.MONEY_STAMP, 1);
        Trader.RegisterTradingRecipe(CorporationMoneyStampRecipe);
        TradingRecipe RawSirthiumRecipe = new TradingRecipe(Items.MONEY, 20, Items.IRRADIATED_SIRTHIUM_RAW, 1);
        Trader.RegisterTradingRecipe(RawSirthiumRecipe);
        return this;
    }

}
