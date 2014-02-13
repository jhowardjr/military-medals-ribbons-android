package org.dress.model;

import org.dress.model.adapter.RibbonDbAdapter;

import android.content.Context;

public final class RibbonViewFactory {
	private RibbonViewFactory() {

	}

	private static final int POS_ZERO = 0;
	private static final int POS_ONE = 1;
	private static final int POS_TWO = 2;
	private static final int POS_THREE = 3;
	private static final int POS_FOUR = 4;
	private static final int POS_FIVE = 5;
	private static final int POS_SIX = 6;
	private static final int POS_SEVEN = 7;
	private static final int POS_EIGHT = 8;
	private static final int POS_NINE = 9;
	private static final int POS_TEN = 10;
	private static final int POS_ELEVEN = 11;
	private static final int POS_TWELEVE = 12;
	private static final int POS_THIRTEEN = 13;

	public static RibbonViewNode getNode(Context context, int value) {
		switch (value) {
		case POS_ZERO:
			return RibbonModel.getRibbonNode(context,
					RibbonDbAdapter.ARMY_DB_ID);
		case POS_ONE:
			return RibbonModel.getRibbonNode(context,
					RibbonDbAdapter.AIRFORCE_DB_ID);
		case POS_TWO:
			return RibbonModel.getRibbonNode(context,
					RibbonDbAdapter.NAVY_DB_ID);
		case POS_THREE:
			return RibbonModel.getRibbonNode(context,
					RibbonDbAdapter.MARINES_DB_ID);
		case POS_FOUR:
			return RibbonModel.getRibbonNode(context,
					RibbonDbAdapter.COAST_DB_ID);
		case POS_FIVE:
			return RibbonModel.getRibbonNode(context, RibbonDbAdapter.LA_DB_ID);
		case POS_SIX:
			return RibbonModel.getRibbonNode(context,
					RibbonDbAdapter.CAPC_DB_ID);
		case POS_SEVEN:
			return RibbonModel.getRibbonNode(context,
					RibbonDbAdapter.CAPS_DB_ID);
		case POS_EIGHT:
			return RibbonModel.getRibbonNode(context,
					RibbonDbAdapter.ARMY_JROTC_DB_ID);
		case POS_NINE:
			return RibbonModel.getRibbonNode(context,
					RibbonDbAdapter.ARMY_ROTC_DB_ID);
		case POS_TEN:
			return RibbonModel.getRibbonNode(context,
					RibbonDbAdapter.AIR_FORCE_JROTC_DB_ID);
		case POS_ELEVEN:
			return RibbonModel.getRibbonNode(context,
					RibbonDbAdapter.AIR_FORCE_ROTC_DB_ID);
		case POS_TWELEVE:
			return RibbonModel.getRibbonNode(context,
					RibbonDbAdapter.NAVY_JROTC_DB_ID);
		case POS_THIRTEEN:
			return RibbonModel.getRibbonNode(context,
					RibbonDbAdapter.NAVY_ROTC_DB_ID);
		default:
			return BranchModel.getBranchNode(context);

		}

	}
}
