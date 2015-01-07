/*
Storybook: Open Source software for novelists and authors.
Copyright (C) 2008 - 2012 Martin Mustun

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package storybook.model.hbn.entity;

import java.awt.Color;

import javax.swing.Icon;

import storybook.toolkit.swing.ColorIcon;
import storybook.toolkit.swing.ColorUtil;

/**
 * Strand generated by hbm2java
 * @hibernate.class
 *   table="STRAND"
 */
public class Strand extends AbstractEntity implements Comparable<Strand> {

	private String abbreviation;
	private String name;
	private Integer color;
	private Integer sort;
	private String notes;

	public Strand() {
	}

	public Strand(String abbreviation, String name, Integer color,
			Integer sort, String notes) {
		this.abbreviation = abbreviation;
		this.name = name;
		this.color = color;
		this.sort = sort;
		this.notes = notes;
	}

	/**
	 * @hibernate.id
	 *   column="ID"
	 *   generator-class="increment"
	 *   unsaved-value="null"
	 */
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @hibernate.property
	 */
	public String getAbbreviation() {
		return this.abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	/**
	 * @hibernate.property
	 */
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @hibernate.property
	 */
	public Integer getColor() {
		return this.color;
	}

	public Color getJColor() {
		if (color == null) {
			return null;
		}
		return new Color(color);
	}

	public String getHTMLColor() {
		return ColorUtil.getHTMLName(getJColor());
	}

	public ColorIcon getColorIcon() {
		return new ColorIcon(getJColor(), 10, 20);
	}

	public void setColor(Integer color) {
		this.color = color;
	}

	public void setJColor(Color color) {
		if (color == null) {
			this.color = null;
			return;
		}
		this.color = color.getRGB();
	}

	/**
	 * @hibernate.property
	 */
	public Integer getSort() {
		return this.sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	/**
	 * @hibernate.property
	 */
	public String getNotes() {
		if (notes == null) {
			return "";
		}
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Override
	public Icon getIcon() {
		return new ColorIcon(getJColor());
	}

	@Override
	public String getAbbr() {
		return abbreviation;
	}

	@Override
	public String toString() {
		return getName() + " (" + getAbbr() + ")";
	}

	@Override
	public boolean equals(Object obj) {
		if (!super.equals(obj)) {
			return false;
		}
		Strand test = (Strand) obj;
		boolean ret = true;
		ret = ret && equalsStringNullValue(name, test.getName());
		ret = ret && equalsStringNullValue(abbreviation, test.getAbbreviation());
		ret = ret && equalsIntegerNullValue(color, test.getColor());
		ret = ret && equalsIntegerNullValue(sort, test.getSort());
		ret = ret && equalsStringNullValue(notes, test.getNotes());
		return ret;
	}

	@Override
	public int hashCode() {
		int hash = super.hashCode();
		hash = hash * 31 + (name != null ? name.hashCode() : 0);
		hash = hash * 31 + (abbreviation != null ? abbreviation.hashCode() : 0);
		hash = hash * 31 + (color != null ? color.hashCode() : 0);
		hash = hash * 31 + (sort != null ? sort.hashCode() : 0);
		hash = hash * 31 + (notes != null ? notes.hashCode() : 0);
		return hash;
	}

	@Override
	public int compareTo(Strand o) {
		if (sort == null && o == null) {
			return 0;
		}
		if (sort != null && o.getSort() == null) {
			return -1;
		}
		if (o.getSort() != null && sort == null) {
			return -1;
		}
		return sort.compareTo(o.getSort());
	}
}