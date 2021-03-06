/*  This file is part of Openrouteservice.
 *
 *  Openrouteservice is free software; you can redistribute it and/or modify it under the terms of the 
 *  GNU Lesser General Public License as published by the Free Software Foundation; either version 2.1 
 *  of the License, or (at your option) any later version.

 *  This library is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; 
 *  without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. 
 *  See the GNU Lesser General Public License for more details.

 *  You should have received a copy of the GNU Lesser General Public License along with this library; 
 *  if not, see <https://www.gnu.org/licenses/>.  
 */
package heigit.ors.routing.graphhopper.extensions.edgefilters;

import java.util.ArrayList;

import com.graphhopper.routing.util.EdgeFilter;
import com.graphhopper.util.EdgeIteratorState;
import heigit.ors.routing.graphhopper.extensions.edgefilters.core.AvoidFeaturesCoreEdgeFilter;

public class EdgeFilterSequence extends ArrayList<EdgeFilter> implements EdgeFilter {

	private String name;
	@Override
	public boolean accept(EdgeIteratorState iter) {
		for (EdgeFilter edgeFilter: this) {
			if (!edgeFilter.accept(iter)) {
				return false;
			}
		}
		return true;
	}

	public String getName(){
		if (this.name == null) {
			String name = "";
			for (EdgeFilter edgeFilter : this) {
				name += "_" + edgeFilter.getClass().getSimpleName();
			}
			return name.toLowerCase();
		}
		else return this.name;
	}

	public void appendName(String name){
		if (this.name == null)
			this.name = ("_" + name);
		else
			this.name += ("_" + name);
	}

	@Override
	public String toString() {
		return "EdgeFilter Sequence :" + size();
	}
}
