package domain.sql.from_string;

import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.function.Predicate;

public class FromString {

    public static void main(String[] args) {
        sqlGetProdectType();
    }

    private static void 工場コードコンボ情報取得() {
        String sql = "SELECT FACTORY_CODE,FACTORY_NAME,FACTORY_NAME_RYAKU FROM PANEL.MS_FACTORY " +
                " WHERE IS_ACTIVE = 0 and OUTSIDE_FLAG = 0";
        System.out.println(sql);

    }

    private static void 対象月取得() {
        StringBuffer sb = new StringBuffer("SELECT ")
                .append("\n")
                .append("MS_MONTHLY_YEARS.MONTHLY_YEARS,MS_FACTORY.FACTORY_NAME ")
                .append("\n")
                .append("FROM MS_MONTHLY_YEARS ")
                .append("\n")
                .append("LEFT JOIN MS_FACTORY ON ")
                .append("\n")
                .append("MS_MONTHLY_YEARS.FACTORY_CODE = MS_FACTORY.FACTORY_CODE AND ")
                .append("\n")
                .append("MS_FACTORY.IS_ACTIVE = 0 ")
                .append("\n")
                .append("WHERE ")
                .append("\n")
                .append("MS_FACTORY.FACTORY_CODE = ? ");

        System.out.println(sb.toString());
    }

    private static void 材料系月報用資産区分() {
        StringBuffer sb = new StringBuffer("SELECT ")
                .append("ASSET_TYPE, UKEHARAI_CODE, DATA_CHARACTERISTICS1 ")
                .append("\n")
                .append("FROM MS_ZAIRYO_UKEHARAI WHERE ")
                .append("\n")
                .append("FACTORY_CODE = '").append("?").append("' AND ")
                .append("\n")
                .append("UKEHARAI_CODE IS NOT NULL AND DATA_CHARACTERISTICS1 IS NOT NULL ")
                .append("\n")
                .append("GROUP BY ASSET_TYPE, UKEHARAI_CODE, DATA_CHARACTERISTICS1 ")
                .append("\n")
                .append("UNION ")
                .append("\n")
                .append("SELECT ")
                .append("\n")
                .append("ASSET_TYPE, UKEHARAI_CODE, DATA_CHARACTERISTICS2 ")
                .append("\n")
                .append("FROM MS_ZAIRYO_UKEHARAI WHERE ")
                .append("\n")
                .append("FACTORY_CODE = '").append("? ").append("' AND ")
                .append("\n")
                .append("UKEHARAI_CODE IS NOT NULL AND DATA_CHARACTERISTICS2 IS NOT NULL ")
                .append("\n")
                .append("GROUP BY ASSET_TYPE, UKEHARAI_CODE, DATA_CHARACTERISTICS2 ")
                .append("\n")
                .append("UNION ")
                .append("\n")
                .append("SELECT ")
                .append("\n")
                .append("ASSET_TYPE, UKEHARAI_CODE, DATA_CHARACTERISTICS ")
                .append("\n")
                .append("FROM TS_ZAIRYO_ERROR_SEND WHERE ")
                .append("FACTORY_CODE = '").append("? ").append("' AND ")
                .append("\n")
                .append("UKEHARAI_CODE IS NOT NULL AND DATA_CHARACTERISTICS IS NOT NULL ")
                .append("\n")
                .append("GROUP BY ASSET_TYPE, UKEHARAI_CODE, DATA_CHARACTERISTICS ");

        System.out.println(sb.toString());

    }

    public static void パネル製品系月報明細書情報取得() {
        StringBuffer sb = new StringBuffer("SELECT ")
                .append("\n")
                .append("FACTORY_CODE, UKEHARAI_TYPE, SLIP_NUMBER, ACCOUNT_PLACE_CODE,")
                .append("\n")
                .append("ATTRIBUTION_PLACE_CODE, COST_SECTION,PRODUCTION_COST_SECTION,")
                .append("\n")
                .append("WAREHOUSE_CODE, DESTINATION_WAREHOUSE_CODE, CUSTOMER_CODE,AREA_CODE,")
                .append("\n")
                .append("ORDER_NUMBER, ORDER_SUB_NUMBER, DESTINATION_AREA_CODE, DESTINATION_ORDER_NUMBER,")
                .append("\n")
                .append("DESTINATION_ORDER_SUB_NUMBER, INSTALLMENT_TYPE, STRAGE_IN_OUT_DATE,")
                .append("\n")
                .append("TRADE_NAME,PRODUCT_TYPE, SML_DIVISION_CODE, TRADE_CODE, SPECIAL_SIZE,")
                .append("\n")
                .append("ARRAY_CODE, DESTINATION_SML_DIVISION_CODE, DESTINATION_TRADE_CODE,")
                .append("\n")
                .append("DESTINATION_SPECIAL_SIZE, DESTINATION_ARRAY_CODE, COST_CLASS_CODE,");
    }

    public static void 景観製品系月報明細書情報取得() {
        StringBuffer sb = new StringBuffer("SELECT ");
        sb.append("FACTORY_CODE, UKEHARAI_TYPE, SLIP_NUMBER, ACCOUNT_PLACE_CODE, ");
        sb.append("ATTRIBUTION_PLACE_CODE, COST_SECTION,PRODUCTION_COST_SECTION, ");
        sb.append("WAREHOUSE_CODE, DESTINATION_WAREHOUSE_CODE, CUSTOMER_CODE,AREA_CODE, ");
        sb.append("ORDER_NUMBER, ORDER_SUB_NUMBER, DESTINATION_AREA_CODE, DESTINATION_ORDER_NUMBER, ");
        sb.append("DESTINATION_ORDER_SUB_NUMBER, INSTALLMENT_TYPE, STRAGE_IN_OUT_DATE, ");
        sb.append("TRADE_NAME,PRODUCT_TYPE, SML_DIVISION_CODE, TRADE_CODE, SPECIAL_SIZE, ");
        sb.append("ARRAY_CODE, DESTINATION_SML_DIVISION_CODE, DESTINATION_TRADE_CODE, ");
        sb.append("DESTINATION_SPECIAL_SIZE, DESTINATION_ARRAY_CODE, COST_CLASS_CODE, ");
        sb.append("ACCOUNT_TITLE_BIG, ACCOUNT_TITLE_MID, ACCOUNT_TITLE_SML, QTY, ");
        sb.append("WEIGHT, UNIT_PRICE, PRICE, ORDER_MONEY, YIELD,SALE_RECOGNITION_TYPE, ");
        sb.append("SALES_DATA_TYPE, STYLE_CODE, ARTICLE_NAME, END_SALES, DESTINATION_ACCOUNT_PLACE_CODE, ");
        sb.append("DESTINATION_ACCOUNT_TITLE_BIG, DESTINATION_ACCOUNT_TITLE_MID, DESTINATION_ACCOUNT_TITLE_SML, ");
        sb.append("BUDGET_NUMBER, REFERENCE_NUMBER,GROUP_CODE, SPECIAL_UKEHARAI_TYPE, COST_UNIT, ");
        sb.append("CREATING_AGENCY_SYSTEM,SEQ,ENTRY_TYPE  ");
        sb.append("FROM ");
        sb.append("TS_SEIHIN_SEND_TO_HEAD  ");
        sb.append("WHERE  ");
        sb.append("FACTORY_CODE = ? ");
        sb.append("AND COST_SECTION = ? ");
        sb.append("AND STRAGE_IN_OUT_DATE BETWEEN ? AND ? ");
        sb.append("ORDER BY ");
        sb.append("COST_CLASS_CODE, UKEHARAI_TYPE, PRODUCTION_COST_SECTION, ORDER_NUMBER, ORDER_SUB_NUMBER,");
        sb.append("SML_DIVISION_CODE, TRADE_CODE, SPECIAL_SIZE, SEQ  ");

    }

    public static void 棚卸処理年月日取得用() {
        StringJoiner joiner = new StringJoiner("\n");
        joiner.add("SELECT STOCK_TAKING_DATE, FACTORY_NAME ")
                .add("FROM MS_FACTORY ")
                .add("LEFT JOIN MS_MONTHLY_YEARS ON ")
                .add("MS_FACTORY.FACTORY_CODE = MS_MONTHLY_YEARS.FACTORY_CODE ")
                .add("WHERE MS_FACTORY.FACTORY_CODE = ? ")
                .add("AND MS_FACTORY.IS_ACTIVE = 0");

        System.out.println(joiner.toString());

    }

    //明細部出力用データ取得用PANEL
    public static void PANEL() {
        //panel

        StringJoiner joiner = new StringJoiner("\n");

        joiner.add("SELECT ");
        joiner.add(
                "TIP.FACTORY_CODE, TIP.PLACE_CODE, MN.NAME1, TIP.PALET_NUMBER, 0 DEL_FLG, NVL(TIP.ORDER_NUMBER, ' ') ORDER_NUMBER, ");
        joiner.add("TIP.BUSINESS_NUMBER, TIP.PAGE_NO_OYA, TIP.PAGE_NO_KO, TIP.TRADE_TYPE, TIP.QUANTITY, ");
        joiner.add("TIP.STOCKTAKING_NUMBER, TIP.INPUT_SEQ, TIP.SECTION_CODE, TIP.PRODUCT_KIND, ");
        joiner.add("TIP.WRITE_WRONG_TYPE, // 指示数、書き損じ      ");
        joiner.add("SUM(NVL(TSP.QUANTITY, 0)) REMAINDER_QTY, SUM(NVL(TSP.STOCK_QTY, 0)) STOCK_QTY, ");
        joiner.add("TIP.INSTALLMENT_NUMBER, TIP.INSTALLMENT_SM_NUMBER ");
        joiner.add("FROM TS_INVENTORY_PANEL TIP ");
        joiner.add(
                "LEFT JOIN MS_NAME MN ON USE_TYPE = 97 AND NAME_CODE = PLACE_CODE AND IS_ACTIVE = 0 AND DELETE_FLG <> 1 ");
        joiner.add("LEFT JOIN TS_STOCK_PANEL TSP ON ");
        joiner.add("TSP.FACTORY_CODE = TIP.FACTORY_CODE AND ");
        joiner.add("TSP.ORDER_NUMBER = TIP.ORDER_NUMBER AND ");
        joiner.add("TSP.PAGE_NO_OYA = TIP.PAGE_NO_OYA AND ");
        joiner.add("TSP.PAGE_NO_KO = TIP.PAGE_NO_KO AND ");
        joiner.add("TO_CHAR(STOCK_DATE, 'yyyy/MM') = TO_CHAR(STOCKTAKING_DATE, 'yyyy/MM') ");
        joiner.add("WHERE ");
        joiner.add("TIP.FACTORY_CODE = '?kojoCd'").add("' AND ");
        joiner.add("TO_CHAR(TIP.STOCKTAKING_DATE, 'yyyy/MM') LIKE '").add("'?stockDate'").add("%' AND ");
        joiner.add("TIP.WAREHOUSE_CODE = '").add("'?warehouseCode'").add("' AND TIP.PRODUCT_KIND <> '4' ");
        joiner.add("if (!orderNumber.equals(空文字)) {");
        joiner.add("AND TIP.ORDER_NUMBER = '").add("?orderNumber");
        joiner.add("}");
        joiner.add("if (!stocktakingNumberS.equals(空白) {");
        joiner.add("AND TIP.STOCKTAKING_NUMBER >= '").add("?stocktakingNumberS ");
        joiner.add("}");
        joiner.add("if (!stocktakingNumberE.equals(空白)) {");
        joiner.add("AND TIP.STOCKTAKING_NUMBER <= '").add("?stocktakingNumberE");
        joiner.add("}");

        joiner.add("if (installmentNumber != 0) {");
        joiner.add("AND TIP.INSTALLMENT_NUMBER = ").add("?installmentNumber");
        joiner.add("}");
        joiner.add("if (installmentSmNumber != 0) {");
        joiner.add("AND TIP.INSTALLMENT_SM_NUMBER = ").add("?installmentSmNumber");
        joiner.add("}");
        joiner.add("if (!paletNumber.equals(空文字)) {");
        joiner.add("AND TIP.PALET_NUMBER = '").add("?paletNumber");
        joiner.add("}");
        joiner.add("GROUP BY ");
        joiner.add(
                "TIP.FACTORY_CODE, TIP.PLACE_CODE, MN.NAME1, TIP.PALET_NUMBER, TIP.ORDER_NUMBER, TIP.BUSINESS_NUMBER, ");
        joiner.add(
                "TIP.PAGE_NO_OYA, TIP.PAGE_NO_KO, TIP.TRADE_TYPE, TIP.QUANTITY, TIP.STOCKTAKING_NUMBER, TIP.INPUT_SEQ, ");
        joiner.add("TIP.SECTION_CODE, TIP.PRODUCT_KIND, ");
        joiner.add("TIP.WRITE_WRONG_TYPE, TIP.INSTALLMENT_NUMBER, TIP.INSTALLMENT_SM_NUMBER ");

        joiner.add(" if (sort == 0) {// 棚卸No順");
        joiner.add("ORDER BY TIP.STOCKTAKING_NUMBER, TIP.INPUT_SEQ ");
        joiner.add("} else if (sort == 1) { // オーダーNo順");
        joiner.add("ORDER BY TIP.ORDER_NUMBER, TIP.INPUT_SEQ ");
        joiner.add("}");

        System.out.println(joiner.toString());
    }

    public static void 明細部出力用データ取得用玄米保冷庫() {
        StringJoiner joiner = new StringJoiner("\n");

        joiner.add("WITH INOUT AS (");
        joiner.add("SELECT ");
        joiner.add("FACTORY_CODE, FA_ORDER_NUMBER, ARRAY_CODE, SUM(IN_QTY) IN_QTY, SUM(OUT_QTY) OUT_QTY ");
        joiner.add("FROM (");
        joiner.add("SELECT FACTORY_CODE, FA_ORDER_NUMBER, ARRAY_CODE, SUM(QTY) IN_QTY, 0 OUT_QTY ");
        joiner.add("FROM TS_SEIHIN_STRAGE_IN ");
        joiner.add("WHERE FACTORY_CODE = ?factoryCode'").add("' AND ");
        joiner.add("TO_CHAR(STRAGE_IN_OUT_DATE, 'yyyy/MM') LIKE ?stockDate%'").add(" AND ARRAY_CODE = 'KOME' ");
        joiner.add("GROUP BY FACTORY_CODE, FA_ORDER_NUMBER, ARRAY_CODE ");
        joiner.add("UNION ALL ");
        joiner.add("SELECT FACTORY_CODE, FA_ORDER_NUMBER, ARRAY_CODE, 0  IN_QTY, SUM(QTY) OUT_QTY ");
        joiner.add("FROM TS_SEIHIN_TAKE_OUT ");
        joiner.add("WHERE FACTORY_CODE = ?factoryCode").add("' AND ");
        joiner.add("TO_CHAR(STRAGE_IN_OUT_DATE, 'yyyy/MM') LIKE ?stockDate%").add(" AND ARRAY_CODE = 'KOME' ");
        joiner.add("GROUP BY FACTORY_CODE, FA_ORDER_NUMBER, ARRAY_CODE ");
        joiner.add(") ");
        joiner.add("GROUP BY FACTORY_CODE, FA_ORDER_NUMBER, ARRAY_CODE ");
        joiner.add(") ");
        joiner.add("SELECT ");
        joiner.add("TIP.FACTORY_CODE, TIP.PLACE_CODE, MN.NAME1, TIP.PALET_NUMBER, 0 DEL_FLG, TIP.ORDER_NUMBER, ");
        joiner.add("TIP.BUSINESS_NUMBER, TIP.PAGE_NO_OYA, TIP.PAGE_NO_KO, TIP.TRADE_TYPE, TIP.QUANTITY, ");
        joiner.add("TIP.STOCKTAKING_NUMBER, TIP.INPUT_SEQ, TIP.SECTION_CODE, TIP.PRODUCT_KIND, ");
        joiner.add("NVL(INOUT.IN_QTY, 0) IN_QTY, NVL(INOUT.OUT_QTY, 0) OUT_QTY, ");
        joiner.add("SUM(NVL(TSK.QUANTITY, 0)) REMAINDER_QTY, SUM(NVL(TSK.STOCK_QTY, 0)) STOCK_QTY ");
        joiner.add("FROM TS_INVENTORY_PANEL TIP ");
        joiner.add(
                "LEFT JOIN MS_NAME MN ON USE_TYPE = 97 AND NAME_CODE = PLACE_CODE AND IS_ACTIVE = 0 AND DELETE_FLG <> 1 ");
        joiner.add("LEFT JOIN TS_STOCK_KOMEKO TSK ON ");
        joiner.add("TSK.FACTORY_CODE = TIP.FACTORY_CODE AND ");
        joiner.add("TSK.ORDER_NUMBER = TIP.ORDER_NUMBER AND ");
        joiner.add("TO_CHAR(TSK.STOCK_DATE, 'yyyy/MM') = TO_CHAR(TIP.STOCKTAKING_DATE, 'yyyy/MM') ");
        joiner.add("LEFT JOIN INOUT ON ");
        joiner.add("TIP.FACTORY_CODE = INOUT.FACTORY_CODE AND ");
        joiner.add("TIP.ORDER_NUMBER = INOUT.FA_ORDER_NUMBER ");
        joiner.add("WHERE ");
        joiner.add("TIP.FACTORY_CODE = ?factoryCode").add(" AND ");
        joiner.add("TO_CHAR(TIP.STOCKTAKING_DATE, 'yyyy/MM') LIKE ?stockDate%").add("AND ");
        joiner.add("TIP.WAREHOUSE_CODE = ?warehouseCode").add("AND TIP.PRODUCT_KIND = '4' ");
        joiner.add("if (!orderNumber.equals(空文字)) {");
        joiner.add("AND TIP.ORDER_NUMBER = ?orderNumber");
        joiner.add("}");
        joiner.add("if (!stocktakingNumberS.equals(void)) {");
        joiner.add("AND TIP.STOCKTAKING_NUMBER >= ?stocktakingNumberS'");
        joiner.add("}");
        joiner.add("if (!stocktakingNumberE.equals(void)) {");
        joiner.add("AND TIP.STOCKTAKING_NUMBER <= ?stocktakingNumberE '");
        joiner.add("}");
        joiner.add("GROUP BY ");
        joiner.add(
                "TIP.FACTORY_CODE, TIP.PLACE_CODE, MN.NAME1, TIP.PALET_NUMBER, TIP.ORDER_NUMBER, TIP.BUSINESS_NUMBER, ");
        joiner.add(
                "TIP.PAGE_NO_OYA, TIP.PAGE_NO_KO, TIP.TRADE_TYPE, TIP.QUANTITY, TIP.STOCKTAKING_NUMBER, TIP.INPUT_SEQ, ");
        joiner.add("TIP.SECTION_CODE, TIP.PRODUCT_KIND, IN_QTY, OUT_QTY ");

        joiner.add("if (sort == 0) { // 棚卸No順");
        joiner.add("ORDER BY TIP.STOCKTAKING_NUMBER, TIP.INPUT_SEQ ");
        joiner.add("} else if (sort == 1) {  オーダーNo順");
        joiner.add("ORDER BY TIP.ORDER_NUMBER, TIP.INPUT_SEQ ");
        joiner.add("}");
        System.out.println(joiner.toString());

    }

    public static void パネル枚数の取得用SQL() {
        StringJoiner joiner = new StringJoiner("\n");
        joiner.add("SELECT NVL(PANEL_NUMBER, 0) PANEL_NUMBER ")
                .add("FROM TS_PANEL_DATA ")
                .add("WHERE FACTORY_CODE = ? ")
                .add("AND ARTICLE_YEAR = ? ")
                .add("AND ARTICLE_NUMBER = ? ")
                .add("AND ORDER_NUMBER = ? ")
                .add("AND INSTALLMENT_NUMBER = ? ")
                .add("AND INSTALLMENT_SM_NUMBER = ? ")
                .add("AND PAGE_NO_OYA = ? ");
        System.out.println(joiner.toString());
    }

    public static void 物件情報の取得用SQL() {
        StringJoiner joiner = new StringJoiner("\n");
        joiner.add("SELECT ")
                .add("ORDER_NUMBER, ARTICLE_YEAR, ARTICLE_NUMBER ")
                .add("FROM TS_ORDER_INFO ")
                .add("WHERE TS_ORDER_INFO.FACTORY_CODE = ? ")
                .add("AND TS_ORDER_INFO.ORDER_NUMBER = ? ")
                .add("ORDER BY ARTICLE_YEAR DESC, ARTICLE_NUMBER DESC");
        System.out.println(joiner.toString());

    }

    public static void 場所名と場所コード() {
        StringJoiner joiner = new StringJoiner("\n");
        joiner.add("SELECT ")
                .add("NAME_CODE,NAME1 ")
                .add("FROM ")
                .add("MS_NAME ")
                .add("WHERE ")
                .add("USE_TYPE=97 AND ")
                .add("DELETE_FLG <>1 AND ")
                .add("IS_ACTIVE = 0 ")
                .add("ORDER BY ")
                .add("NAME_CODE");
        System.out.println(joiner.toString());

    }

    public static void 標識コード取得用SQL() {
        StringJoiner joiner = new StringJoiner("\n");

        joiner.add("SELECT DISTINGUISH_CODE ")
                .add("FROM MS_COUNTER")
                .add(" WHERE FACTORY_CODE = '")
                .add("?factoryCode")
                .add("AND NUMBERING_TYPE = ")
                .add("?numberingType");
        System.out.println(joiner.toString());

    }

    public static void 物件情報の取得用SQL更新() {
        StringJoiner joiner = new StringJoiner("\n");

        joiner.add("SELECT ")
                .add("ORDER_NUMBER, ARTICLE_YEAR, ARTICLE_NUMBER ")
                .add("FROM TS_ORDER_INFO ")
                .add("WHERE TS_ORDER_INFO.FACTORY_CODE = ? ")
                .add("AND TS_ORDER_INFO.ORDER_NUMBER = ? ")
                .add("ORDER BY ARTICLE_YEAR DESC, ARTICLE_NUMBER DESC");
        System.out.println(joiner.toString());

    }

    public static void 分納番号_分納小番号の取得用SQL更新用() {
        StringJoiner joiner = new StringJoiner("\n");

        joiner.add("SELECT ");
        joiner.add("INSTALLMENT_NUMBER, INSTALLMENT_SM_NUMBER ");
        joiner.add("FROM TS_PANEL_DATA ");
        joiner.add("WHERE FACTORY_CODE = ?factoryCode' AND ");
        joiner.add("AND ARTICLE_YEAR = ?articleYear");
        joiner.add("AND ARTICLE_NUMBER = ?articleNumber");
        joiner.add("AND ORDER_NUMBER = ?orderNumber");
        joiner.add("AND PAGE_NO_OYA = ?pageNoOya");
        System.out.println(joiner.toString());

    }

    public static void 業連番号の取得用SQL() {
        StringJoiner joiner = new StringJoiner("\n");

        joiner.add("SELECT ");
        joiner.add("FACTORY_CODE, ORDER_NUMBER, BUSINESS_NUMBER, ARTICLE_YEAR, ARTICLE_NUMBER ");
        joiner.add("FROM TS_ORDER_INFO ");
        joiner.add("WHERE ");
        joiner.add("if (!\"\".equals(orderNumber)) {");
        joiner.add("FACTORY_CODE = '?factoryCode'");
        joiner.add("AND ORDER_NUMBER = 'orderNumber'");
        joiner.add("ORDER BY ARTICLE_YEAR DESC, ARTICLE_NUMBER DESC ");
        joiner.add(")} else if (!\"\".equals(?businessNumber)) {");
        joiner.add("FACTORY_CODE ='?factoryCode'");
        joiner.add("AND  BUSINESS_NUMBER = '?businessNumber'");
        joiner.add("ORDER BY ARTICLE_YEAR DESC, ARTICLE_NUMBER DESC ");
        System.out.println(joiner.toString());

    }

    public static void 品名コード業連の取得用SQL玄米保冷庫() {
        StringJoiner joiner = new StringJoiner("\n");

        joiner.add("SELECT ORDER_NUMBER TRADE_NAME, BUSINESS_NUMBER ");
        joiner.add("FROM MS_KOMEKO ")
                .add("WHERE FACTORY_CODE ='?PNLFCommonProductPlanConst.FACTORY_CODE_SIGA'")
                .add(" AND TRADE_CODE = '?orderNumber'")
                .add(" AND ROWNUM = 1");

        System.out.println(joiner.toString());
    }

    public static void 品名コード業連の取得用SQLPANEL() {
        StringJoiner joiner = new StringJoiner("\n");

        joiner.add("SELECT TPD.TRADE_NAME, BUSINESS_NUMBER ");
        joiner.add("FROM TS_PANEL_DATA TPD LEFT JOIN TS_ORDER_INFO TOI ON ")
                .add("TPD.FACTORY_CODE = ?TOI.FACTORY_CODE ")
                .add("AND TPD.ARTICLE_YEAR = ?TOI.ARTICLE_YEAR ")
                .add("AND TPD.ARTICLE_NUMBER = ?TOI.ARTICLE_NUMBER ")
                .add("AND TPD.ORDER_NUMBER = ?TOI.ORDER_NUMBER ")
                .add("WHERE TPD.FACTORY_CODE = '?factoryCode'")
                .add(" AND TPD.ARTICLE_YEAR = '?articleYear'")
                .add(" AND TPD.ARTICLE_NUMBER = '?articleNumber'")
                .add(" AND TPD.ORDER_NUMBER = '?orderNumber'")
                .add(" AND TPD.PAGE_NO_OYA = '?pageNoOya'")
                .add(" AND ROWNUM = 1");

        System.out.println(joiner.toString());

    }

    public static void 材料系受払条件項目マスタ() {
        StringJoiner joiner = new StringJoiner("\n");

        joiner.add("SELECT  DISTINCT ")
                .add("EXPENDITURE_TYPE,UKEHARAI_NAME ")
                .add("FROM ")
                .add("MS_ZAIRYO_UKEHARAI ")
                .add("WHERE ")
                .add("FACTORY_CODE=?factoryCode")
                .add("AND ASSET_TYPE='J'")
                .add(" AND (DATA_CHARACTERISTICS1 NOT LIKE 'Z%' OR DATA_CHARACTERISTICS1 IS NULL)  ")
                .add("if factoryCode ==409{")
                .add("AND EXPENDITURE_TYPE<>'901' ")
                .add("} ")
                .add("   if factoryCode !=409 {")
                .add("--他工場は倉移管を外す         ")
                .add("AND EXPENDITURE_TYPE NOT IN ('901', '905', '935') ")
                .add("} ")
                .add("ORDER BY EXPENDITURE_TYPE");

        System.out.println(joiner.toString());

    }

    public static void 貯蔵品入出庫エントリ_印刷ボタン押下時() {
        StringJoiner joiner = new StringJoiner("\n");

        joiner.add("SELECT DISTINCT ");
        joiner.add("FACTORY_CODE, ");
        joiner.add("RESULTS_WAREHOUSE, ");
        joiner.add("ACCOUNT_PLACE_CODE, ");
        joiner.add("UKEHARAI_TYPE, ");
        joiner.add("NAME, ");
        joiner.add("OWN_SECTION, ");
        joiner.add("DESTINATION_SECTION, ");
        joiner.add("DESTINATION_WAREHOUSE ");
        joiner.add("FROM (SELECT /*+ INDEX(Z_IN TS_ZAIRYO_STRAGE_IN_IK2) */ ");
        joiner.add("FACTORY_CODE, ");
        joiner.add("RESULTS_WAREHOUSE, ");
        joiner.add("ACCOUNT_PLACE_CODE, ");
        joiner.add("INTAKE_TYPE UKEHARAI_TYPE, ");
        joiner.add("MS1.NAME1 NAME, ");
        joiner.add("OWN_SECTION, ");
        joiner.add("DESTINATION_SECTION, ");
        joiner.add("DESTINATION_WAREHOUSE ");
        joiner.add("FROM TS_ZAIRYO_STRAGE_IN Z_IN ");
        joiner.add("LEFT JOIN MS_NAME MS1 ");
        joiner.add("ON Z_IN.INTAKE_TYPE = MS1.NAME_CODE ");
        joiner.add("AND MS1.USE_TYPE = '104' ");

        joiner.add("equal shiga ==factoryCode{")
                .add("WHERE Z_IN.FACTORY_CODE IN ('?factoryCode', senagawa)")
                .add("}")
                .add("notEqual shiga ==factoryCode{")

                .add("WHERE Z_IN.FACTORY_CODE = '?factoryCode'")
                .add("}");

        joiner.add("AND Z_IN.FINANCIAL_DATE BETWEEN '?firstDay' AND  '?lastDay'");
        joiner.add("AND Z_IN.ASSET_TYPE = 'J' ");
        joiner.add("AND ENTRY_TYPE = '1' ");
        joiner.add("AND Z_IN.CREATE_USER = '?personCode'");

        joiner.add("UNION SELECT /*+ INDEX(Z_OUT TS_ZAIRYO_TAKE_OUT_IK2) */ ");
        joiner.add("FACTORY_CODE, ");
        joiner.add("RESULTS_TAKEOUT RESULTS_WAREHOUSE, ");
        joiner.add("ACCOUNT_PLACE_CODE, ");
        joiner.add("EXPENDITURE_TYPE, ");
        joiner.add("MS1.NAME1 NAME, ");
        joiner.add("OWN_SECTION, ");
        joiner.add("DESTINATION_SECTION, ");
        joiner.add("DESTINATION_WAREHOUSE ");
        joiner.add("FROM TS_ZAIRYO_TAKE_OUT Z_OUT ");
        joiner.add("LEFT JOIN MS_NAME MS1 ");
        joiner.add("ON Z_OUT.EXPENDITURE_TYPE = MS1.NAME_CODE ");
        joiner.add("AND MS1.USE_TYPE = '104'--定数 ");

        joiner.add("equal shiga ==factoryCode{");
        joiner.add("WHERE Z_OUT.FACTORY_CODE IN (?factoryCode,senagawa)'");

        joiner.add("}");
        joiner.add("　notequal shiga ==factoryCode{");
        joiner.add("WHERE Z_OUT.FACTORY_CODE = 'factoryCode'");
        joiner.add("}");

        joiner.add("AND Z_OUT.FINANCIAL_DATE BETWEEN '?firstDay AND 'lastDay' ");
        joiner.add("AND Z_OUT.ASSET_TYPE = 'J' ");
        joiner.add("AND ENTRY_TYPE = '1' ");
        joiner.add("AND Z_OUT.CREATE_USER = 'personCode' ");

        joiner.add(") TS_ZAIRYO ");
        joiner.add("ORDER BY ");
        joiner.add("RESULTS_WAREHOUSE, ACCOUNT_PLACE_CODE, UKEHARAI_TYPE, OWN_SECTION, ");
        joiner.add("DESTINATION_WAREHOUSE, DESTINATION_SECTION ");

        //        }else if(expenditureType.equals("901")||expenditureType.equals("903")||expenditureType.equals("904")||expenditureType.equals("905")||expenditureType.equals("906")||expenditureType.equals("907")||expenditureType.equals("908")||expenditureType.equals("910")||expenditureType.equals("911")||expenditureType.equals("912")||expenditureType.equals("913")){joiner.add("SELECT /*+ INDEX(Z_IN TS_ZAIRYO_STRAGE_IN_IK2) */ DISTINCT ");joiner.add("FACTORY_CODE, ");joiner.add("RESULTS_WAREHOUSE, ");joiner.add("ACCOUNT_PLACE_CODE, ");joiner.add("INTAKE_TYPE UKEHARAI_TYPE, ");joiner.add("MS1.NAME1 NAME, ");joiner.add("OWN_SECTION, ");joiner.add("DESTINATION_SECTION, ");joiner.add("DESTINATION_WAREHOUSE ");joiner.add("FROM TS_ZAIRYO_STRAGE_IN Z_IN ");joiner.add("LEFT JOIN MS_NAME MS1 ");joiner.add("ON Z_IN.INTAKE_TYPE = MS1.NAME_CODE ");joiner.add("AND MS1.USE_TYPE = 104 ");
        //
        //if(PNLFCommonProductPlanConst.FACTORY_CODE_SIGA.equals(factoryCode)){joiner.add("WHERE Z_IN.FACTORY_CODE IN ('").append(factoryCode).append("', '").add(PNLFCommonProductPlanConst.FACTORY_CODE_SENA).append("') ");}else{joiner.add("WHERE Z_IN.FACTORY_CODE = '").append(factoryCode).append("' ");}
        //
        //joiner.add("AND Z_IN.FINANCIAL_DATE BETWEEN '").append(firstDay).append("' AND '").append(lastDay).append("' ");joiner.add("AND INTAKE_TYPE = '").append(expenditureType).append("' ");joiner.add("AND ENTRY_TYPE = '1' ");joiner.add("AND Z_IN.CREATE_USER = '").append(personCode).append("' ");
        //
        //joiner.add("ORDER BY ");joiner.add("RESULTS_WAREHOUSE, ACCOUNT_PLACE_CODE, OWN_SECTION, ");joiner.add("DESTINATION_WAREHOUSE, DESTINATION_SECTION ");
        //
        //}else if(expenditureType.equals("920")||expenditureType.equals("921")||expenditureType.equals("924")||expenditureType.equals("925")||expenditureType.equals("927")||expenditureType.equals("928")||expenditureType.equals("930")||expenditureType.equals("931")||expenditureType.equals("932")||expenditureType.equals("933")||expenditureType.equals("935")||expenditureType.equals("936")){joiner.add("SELECT /*+ INDEX(Z_OUT TS_ZAIRYO_TAKE_OUT_IK2) */ DISTINCT ");joiner.add("FACTORY_CODE, ");joiner.add("RESULTS_TAKEOUT RESULTS_WAREHOUSE, ");joiner.add("ACCOUNT_PLACE_CODE, ");joiner.add("EXPENDITURE_TYPE UKEHARAI_TYPE, ");joiner.add("MS1.NAME1 NAME, ");joiner.add("OWN_SECTION, ");joiner.add("DESTINATION_SECTION, ");joiner.add("DESTINATION_WAREHOUSE ");joiner.add("FROM TS_ZAIRYO_TAKE_OUT Z_OUT ");joiner.add("LEFT JOIN MS_NAME MS1 ");joiner.add("ON Z_OUT.EXPENDITURE_TYPE = MS1.NAME_CODE ");joiner.add("AND MS1.USE_TYPE = 104 ");
        //
        //if(PNLFCommonProductPlanConst.FACTORY_CODE_SIGA.equals(factoryCode)){joiner.add("WHERE Z_OUT.FACTORY_CODE IN ('").add(factoryCode).add("', '").add(PNLFCommonProductPlanConst.FACTORY_CODE_SENA).add("') ");}else{joiner.add("WHERE Z_OUT.FACTORY_CODE = '").add(factoryCode).add("' ");}
        //
        //joiner.add("AND Z_OUT.FINANCIAL_DATE BETWEEN '").add(firstDay).add("' AND '").add(lastDay).add("' ");joiner.add("AND EXPENDITURE_TYPE = '").add(expenditureType).add("' ");joiner.add("AND ENTRY_TYPE = '1' ");joiner.add("AND Z_OUT.CREATE_USER = '").add(personCode).add("' ");
        //
        //joiner.add("ORDER BY ");joiner.add("RESULTS_WAREHOUSE, ACCOUNT_PLACE_CODE, OWN_SECTION, ");joiner.add("DESTINATION_WAREHOUSE, DESTINATION_SECTION ");
        //
        System.out.println(joiner.toString());

    }

    public static void 貯蔵品入出庫エントリ画面_表示時のモデル用情報取得SQL() {
        StringJoiner joiner = new StringJoiner("\n");

        joiner.add("SELECT ")
                .add("ACCOUNT_PLACE_CHECK, ACCOUNT_PLACE_CODE, ")
                .add("DESTINATION_SECTION_CHECK, DESTINATION_SECTION, ")
                .add("DESTINATION_WAREHOUSE_CHECK, DESTINATION_WAREHOUSE, ")
                .add("OWN_SECTION_CHECK, OWN_SECTION, ")
                .add("TRADE_CODE_CHECK, ")
                .add("QUANTITY_CHECK, QUANTITY, ")
                .add("ORDER_NUMBER_CHECK, ORDER_NUMBER,")
                .add("CUSTOMER_CHECK, CUSTOMER_CODE, ")
                .add("UNIT_PRICE_CHECK, UNIT_PRICE, ")
                .add("PRICE_CHECK, PRICE, ")
                .add("BUDGET_NUMBER_CHECK, BUDGET_NUMBER ")
                .add("FROM MS_ZAIRYO_UKEHARAI WHERE ")
                .add("FACTORY_CODE = ? AND EXPENDITURE_TYPE = ? ");
        joiner.add("--相手部門がnull Or blankでない場合   destinationSection{ ");
        joiner.add("AND DESTINATION_SECTION = ? ");
        joiner.add("}");
        joiner.add("ORDER BY DESTINATION_SECTION ");

        System.out.println(joiner.toString());

    }

    public static void 貯蔵品入出庫エントリ_部品マスタ() {
        StringJoiner joiner = new StringJoiner("\n");

        joiner.add("SELECT ")
                .add("PARTS_NAME, PARTS_SIZE, ONEROUS_PROVISION_UNIT_PRICE, IN_SIDE_TYPE ")
                .add("FROM")
                .add("MS_PARTS2")
                .add("WHERE")
                .add("--PARTS_CODE=画面値品名コード")
                .add("PARTS_CODE = ? ")
                .add("AND RESERVE_PARTS_TYPE=1 ")
                .add("AND IS_ACTIVE=0 AND DELETE_FLG<>1 ");

        System.out.println(joiner.toString());

    }

    public static void 貯蔵品入出庫エントリ_受注情報テーブル() {
        StringJoiner joiner = new StringJoiner("\n");

        joiner.add("SELECT ARTICLE_YEAR, ARTICLE_NUMBER ");
        joiner.add("FROM TS_ORDER_INFO ");
        joiner.add("WHERE ");
        joiner.add("FACTORY_CODE = '?factoryCode' ");
        joiner.add("AND ORDER_NUMBER = '?orderNumbe'");
        joiner.add("AND FACTORY_ORDER_FLAG = 0 ");
        joiner.add("ORDER BY ARTICLE_YEAR DESC, ARTICLE_NUMBER DESC ");

        System.out.println(joiner.toString());

    }

    public static void 貯蔵品入出庫エントリ_オーダーテーブル履歴() {
        StringJoiner joiner = new StringJoiner("\n");

        joiner.add("SELECT TO_CHAR(SALES_COMPLET_DATE, 'yyyy/MM') SALES_COMPLET_DATE");
        joiner.add(" FROM FAS_ORDER WHERE ");
        joiner.add("ARTICLE_YEAR = 'articleYear'");
        joiner.add(" AND ARTICLE_NUMBER = 'articleNumber' ");
        joiner.add(" AND ORDER_NUMBER = 'orderNumber'");
        joiner.add(" ORDER BY REVISION DESC ");

        System.out.println(joiner.toString());

    }

    public static void 発送部品区分取得用_品名検索() {
        StringJoiner joiner = new StringJoiner("\n");

        joiner.add("select NAME_CODE, NAME1 ")
                .add("from MS_NAME ")
                .add("where IS_ACTIVE = 0 ")
                .add("and USE_TYPE = 48 ")
                .add("and DELETE_FLG <> 1 ")
                .add("order by NAME_CODE");

        System.out.println(joiner.toString());

    }

    public static void 常備非常備_品名検索() {
        StringJoiner joiner = new StringJoiner("\n");

        joiner.add("select NAME_CODE, NAME1 ")
                .add("from MS_NAME ")
                .add("where IS_ACTIVE = 0 ")
                .add("and USE_TYPE = 49 ")
                .add("and DELETE_FLG <> 1 ")
                .add("order by NAME_CODE");

        System.out.println(joiner.toString());

    }

    public static void 検索_品名検索() {
        StringJoiner joiner = new StringJoiner("\n");

        joiner.add("SELECT ")
                .add("MS_PARTS2.PARTS_CODE, MS_PARTS2.PARTS_NAME,MS_PARTS2.PARTS_SIZE, ")
                .add("MS_PARTS2.PRODUCT_SHIP_TYPE,MS_PARTS2.RESERVE_PARTS_TYPE, MS_PARTS2.UNIT ")
                .add("FROM MS_PARTS2 ")
                .add("WHERE MS_PARTS2.IS_ACTIVE = 0 AND MS_PARTS2.DELETE_FLG = 0 ")
                .add("もし productType != -1 の時{")
                .add(" AND MS_PARTS2.PRODUCT_SHIP_TYPE = --'productType' ")
                .add("}")
                .add("もし reservePartsType != -1{")
                .add(" AND MS_PARTS2.RESERVE_PARTS_TYPE ='reservePartsType' ")
                .add("}")
                .add(" AND  MS_PARTS2.PARTS_NAME LIKE '%partsName%' ")
                .add(" ORDER BY MS_PARTS2.PARTS_CODE");

        System.out.println(joiner.toString());

    }

    public static void 更新前チェック_部品マスタ() {
        StringJoiner joiner = new StringJoiner("\n");

        joiner.add("SELECT ")
                .add("PARTS_NAME,")
                .add(" PARTS_SIZE, ")
                .add("ONEROUS_PROVISION_UNIT_PRICE,")
                .add(" IN_SIDE_TYPE ")
                .add("FROM MS_PARTS2 WHERE PARTS_CODE = ?")
                .add(" AND RESERVE_PARTS_TYPE=1")
                .add(" AND IS_ACTIVE=0 AND DELETE_FLG<>1 ");

        System.out.println(joiner.toString());

    }

    public static void 枠材重量計算に使用する情報を取得() {
        StringJoiner joiner = new StringJoiner("\n");

        joiner.add("SELECT UNIT_WEIGHT,")
                .add(" STANDARD_LENGTH")
                .add(" FROM")
                .add(" MS_FRAME ")
                .add("WHERE ")
                .add("UKEHARAI_TRADE_CODE = ? ");

        System.out.println(joiner.toString());

    }

    public static void 材料系共通メソッド_材料系受払条件項目取得ＳＱＬ() {
        StringJoiner joiner = new StringJoiner("\n");

        joiner.add("SELECT ")
                .add("NVL(ACCOUNT_RANGE_TYPE, 0) ACCOUNT_RANGE_TYPE, ") // [1]決算作成対象外区分    
                .add("NVL(UKEHARAI_RANGE_TYPE, 0) UKEHARAI_RANGE_TYPE, ") // [2]受払対象外区分  
                .add("NVL(UKEHARAI_TOTAL_RANGE_TYPE, 0) UKEHARAI_TOTAL_RANGE_TYPE, ") // [3]受払トータル対象外区分  
                .add("DESTINATION_SECTION, ") // [4]相手部門  
                .add("COST_TOTALIZATION_CODE, ") // [5]費用集計コード   
                .add("UKEHARAI_CODE, ") // [6]受払内容コード   
                .add("ACCOUNT_PLACE_CODE, ") // [7]勘定事業所 
                .add("TRADE_CODE, ") // [8]品名コード（資産評価組コード）   
                .add("COST_CLASS_CODE, ") // [9]相手品名コード（原価組コード）   
                .add("BUDGET_NUMBER, ") // [10]予算番号 
                .add("ASSET_TYPE, ") // [11]資産区分 
                .add("OWN_WAREHOUSE, ") // [12]自倉庫  
                .add("OWN_SECTION, ") // [13]自部門  
                .add("ISSUE_PLACE, ") // [14]発行事業所    
                .add("KATAZAI_USED_SECTION, ") // [15]形材　使用部門  
                .add("DATA_CHARACTERISTICS1, ") // [16]データ標識１   
                .add("DATA_CHARACTERISTICS2 ") // [17]データ標識２   
                .add("FROM MS_ZAIRYO_UKEHARAI WHERE ")
                .add("FACTORY_CODE = ? AND ")
                .add("EXPENDITURE_TYPE = ? ");
        joiner.add("もし相手部門の値がnullでないなら(!.equals(this.exceptNull(destSection))) { ");
        joiner.add("AND DESTINATION_SECTION = ? --destSection");
        joiner.add("}");

        System.out.println(joiner.toString());

    }

    public static void 管理部門取得ＳＱＬ_貯蔵品() {
        StringJoiner joiner = new StringJoiner("\n");

        joiner.add("SELECT ")
                .add("MANAGEMENT_SECTION, EXPENDITURE_SECTION ")
                .add("FROM MS_PARTS_UNIT_PRICE WHERE ")
                .add("FACTORY_CODE = 'factoryCode' ")
                .add("AND PARTS_CODE = 'partsCode'")
                .add("AND OPERATION_START =")
                .add("( SELECT MAX(OPERATION_START) FROM MS_PARTS_UNIT_PRICE ")
                .add("WHERE FACTORY_CODE = 'factoryCode).append'  ")
                .add("AND PARTS_CODE = 'partsCode' ")
                .add("AND TO_CHAR(OPERATION_START, 'yyyy/MM/dd') <= 'opeStart'");
           
        System.out.println(joiner.toString());

    }

    public static void 内作単価_使用部門取得SQL_貯蔵品() {
        StringJoiner joiner = new StringJoiner("\n");

        String factoryCode = "'factoryCode'";
        String articleYear = "'articleYear'";
        String articleNumber = "'articleNumber'";
        String orderNumber = "'orderNumber'";
        String specialPartsNo = "'specialPartsNo'";

        joiner.add("SELECT ")
                .add("NVL(PROCESSING_COST, 0) + NVL(MATERIALCOST, 0), ")
                .add("STORED_GOODS_USED_SECTION ")
                .add("FROM")
                .add("TS_SPECIAL_PARTS ")
                .add("WHERE ")
                .add("FACTORY_CODE =  " + factoryCode)
                .add("AND ARTICLE_YEAR = " + articleYear)
                .add("AND  ARTICLE_NUMBER =" + articleNumber)
                .add("AND ORDER_NUMBER = " + orderNumber)
                .add("AND SPECIAL_PARTS_NO = " + specialPartsNo);

        System.out.println(joiner.toString());

    }

    public static void 内作単価マスタ検索SQL_貯蔵品() {

        //getMsInSideUnitPrice
        StringJoiner joiner = new StringJoiner("\n");

        String factoryCode = "'factoryCode'";
        String articleYear = "'articleYear'";
        String articleNumber = "'articleNumber'";
        String orderNumber = "'orderNumber'";
        String specialPartsNo = "'specialPartsNo'";
        String partsCode = "'partsCode'";
        String opeStart = "'opeStart'";

        // 工場コードは409固定で検索を行なう。
        joiner.add("SELECT ")
                .add("MATERIALS_COST, PROCESSING_UNIT_PRICE, UNIT_PRICE ")
                .add("FROM MS_IN_SIDE_UNIT_PRICE ")
                .add("WHERE FACTORY_CODE = '409' ")
                .add("AND PARTS_CODE = " + partsCode)
                .add("AND OPERATION_START = ")
                .add("( SELECT MAX(OPERATION_START) FROM MS_IN_SIDE_UNIT_PRICE ")
                .add("WHERE FACTORY_CODE = '409'--固定値 ")
                .add("AND PARTS_CODE = " + partsCode)
                .add("AND TO_CHAR(OPERATION_START, 'yyyy/MM/dd') <= " + opeStart)
                .add(")");

        System.out.println(joiner.toString());

    }

    /**
     * 貯蔵品入出庫エントリ : 製品区分取得SQL
     */
    public static void sqlGetProdectType() {

        //getMsInSideUnitPrice
        StringJoiner joiner = new StringJoiner("\n");

        String factoryCode = "'factoryCode'";
        String articleYear = "'articleYear'";
        String articleNumber = "'articleNumber'";
        String orderNumber = "'orderNumber'";
        String specialPartsNo = "'specialPartsNo'";
        String partsCode = "'partsCode'";
        String opeStart = "'opeStart'";

        joiner.add("SELECT ")
                .add("ARTICLE_YEAR, ")
                .add("ARTICLE_NUMBER, ")
                .add("GOODS_TYPE ")
                .add("FROM ")
                .add("TS_ORDER_MANAGE")
                .add(" WHERE ")
                .add("FACTORY_CODE = ?")
                .add(" AND ORDER_NUMBER = ? ")
                .add("ORDER BY ")
                .add("ARTICLE_YEAR DESC,")
                .add(" ARTICLE_NUMBER DESC ");

        System.out.println(joiner.toString());

    }

}
