package org.dress.model;

public class RibbonViewFactory {

	public static RibbonViewNode getNode(int value) {
		switch (value) {
		case 0:
			return RibbonModel.getRibbonNode(1);
		case 1:
			return RibbonModel.getRibbonNode(3);
		case 2:
			return RibbonModel.getRibbonNode(2);
		case 3:
			return RibbonModel.getRibbonNode(4);
		case 4:
			return RibbonModel.getRibbonNode(5);
		case 5:
			return RibbonModel.getRibbonNode(6);
		case 6:
			return RibbonModel.getRibbonNode(7);
		case 7:
			return RibbonModel.getRibbonNode(8);
		case 8:
			return RibbonModel.getRibbonNode(12);
		case 9:
			return RibbonModel.getRibbonNode(9);
		case 10:
			return RibbonModel.getRibbonNode(13);
		case 11:
			return RibbonModel.getRibbonNode(10);
		case 12:
			return RibbonModel.getRibbonNode(11);
		case 13:
			return RibbonModel.getRibbonNode(14);
		default:
			return BranchModel.getBranchNode();

		}

	}
}
