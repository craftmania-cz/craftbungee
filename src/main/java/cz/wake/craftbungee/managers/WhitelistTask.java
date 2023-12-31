package cz.wake.craftbungee.managers;

import cz.wake.craftbungee.Main;
import cz.wake.craftbungee.listeners.VPNListener;
import cz.wake.craftbungee.objects.BlacklistedASN;
import cz.wake.craftbungee.utils.Logger;
import cz.wake.craftbungee.objects.WhitelistedIP;
import cz.wake.craftbungee.objects.WhitelistedNames;

import java.util.List;

public class WhitelistTask implements Runnable {

    @Override
    public void run() {

        // IP Whitelist
        List<WhitelistedIP> ips = Main.getInstance().getSQLManager().getWhitelistedIPs();
        VPNListener.getAllowedIps().clear();
        VPNListener.setAllowedIps(ips);
        Logger.info("Update IP adres na whitelistu, celkem (" + ips.size() + ").");

        // Nick Whitelist
        List<WhitelistedNames> names = Main.getInstance().getSQLManager().getWhitelistedNames();
        VPNListener.getAllowedNames().clear();
        VPNListener.setAllowedNames(names);
        Logger.info("Update Nicku na whitelistu, celkem (" + names.size() + ").");

        // ASN Blacklist
        List<BlacklistedASN> asns = Main.getInstance().getSQLManager().getBlacklistedASNs();
        VPNListener.getBlacklistedASNs().clear();
        VPNListener.setBlacklistedASNs(asns);
        Logger.info("Update ASN databáze dokočen, celkem (" + asns.size() + ").");

        Logger.success("Update IP & Nick Whitelistu dokoncen!");
    }
}
