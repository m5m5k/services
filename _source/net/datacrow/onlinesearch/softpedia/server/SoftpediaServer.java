/******************************************************************************
 *                                     __                                     *
 *                              <-----/@@\----->                              *
 *                             <-< <  \\//  > >->                             *
 *                               <-<-\ __ /->->                               *
 *                               Data /  \ Crow                               *
 *                                   ^    ^                                   *
 *                              info@datacrow.net                             *
 *                                                                            *
 *                       This file is part of Data Crow.                      *
 *       Data Crow is free software; you can redistribute it and/or           *
 *        modify it under the terms of the GNU General Public                 *
 *       License as published by the Free Software Foundation; either         *
 *              version 3 of the License, or any later version.               *
 *                                                                            *
 *        Data Crow is distributed in the hope that it will be useful,        *
 *      but WITHOUT ANY WARRANTY; without even the implied warranty of        *
 *           MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.             *
 *           See the GNU General Public License for more details.             *
 *                                                                            *
 *        You should have received a copy of the GNU General Public           *
 *  License along with this program. If not, see http://www.gnu.org/licenses  *
 *                                                                            *
 ******************************************************************************/

package net.datacrow.onlinesearch.softpedia.server;

import java.util.ArrayList;
import java.util.Collection;

import net.datacrow.core.modules.DcModules;
import net.datacrow.core.objects.DcObject;
import net.datacrow.core.services.IOnlineSearchClient;
import net.datacrow.core.services.Region;
import net.datacrow.core.services.SearchMode;
import net.datacrow.core.services.SearchTask;
import net.datacrow.core.services.plugin.IServer;
import net.datacrow.core.settings.Setting;
import net.datacrow.onlinesearch.softpedia.mode.DriverSearchMode;
import net.datacrow.onlinesearch.softpedia.mode.GameSearchMode;
import net.datacrow.onlinesearch.softpedia.mode.LinuxSearchMode;
import net.datacrow.onlinesearch.softpedia.mode.MacSearchMode;
import net.datacrow.onlinesearch.softpedia.mode.WindowsSearchMode;
import net.datacrow.onlinesearch.softpedia.task.SoftpediaSearch;

public class SoftpediaServer implements IServer {
    
    private Collection<Region> regions = new ArrayList<Region>();
    private Collection<SearchMode> modes = new ArrayList<SearchMode>();
    
    public SoftpediaServer() {
        regions.add(new Region("en", "Default (english)", "http://www.softpedia.com"));
        modes.add(new LinuxSearchMode());
        modes.add(new WindowsSearchMode());
        modes.add(new MacSearchMode());
        modes.add(new GameSearchMode());
        modes.add(new DriverSearchMode());
    }
    
    public String getName() {
        return "Softpedia";
    }

    public Collection<Region> getRegions() {
        return regions;
    }

    public Collection<SearchMode> getSearchModes() {
        return modes;
    }

    public String getUrl() {
        return "http://www.softpedia.com";
    }

    public Collection<Setting> getSettings() {
        return null;
    }   
    
    @Override
    public int getModule() {
        return DcModules._SOFTWARE;
    }
    
    public SearchTask getSearchTask(IOnlineSearchClient listener,
            SearchMode mode, 
            Region region, 
            String query,
            DcObject client) {

        SoftpediaSearch task = new SoftpediaSearch(listener, this, mode, query);
        task.setClient(client);
        return task;
    }    
    @Override
    public String toString() {
        return getName();
    }
}
