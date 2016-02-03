package com.dc.esb.servicegov.export.bean;

/**
 * Created by Administrator on 2016/2/3.
 */
public class WordTemplate {
    //替换符
    public static final String categoryTitle = "${categoryTitle}";
    public static final String serviceTitle = "${serviceTitle}";
    public static final String serviceDesc = "${serviceDesc}";
    public static final String operationTitle = "${operationTitle}";
    public static final String operationDesc = "${operationDesc}";
    public static final String providers = "${providers}";
    public static final String consumers = "${consumers}";
    public static final String reqSeq = "${reqSeq}";
    public static final String reqStructName = "${reqStructName}";
    public static final String reqType = "${reqType}";
    public static final String reqStructAlias = "${reqStructAlias}";
    public static final String reqRequired = "${reqRequired}";
    public static final String reqConstraint = "${reqConstraint}";
    public static final String reqRemark = "${reqRemark}";
    public static final String rspSeq = "${rspSeq}";
    public static final String rspStructName = "${rspStructName}";
    public static final String rspType = "${rspType}";
    public static final String rspStructAlias = "${rspStructAlias}";
    public static final String rspRequired = "${rspRequired}";
    public static final String rspConstraint = "${rspConstraint}";
    public static final String rspRemark = "${rspRemark}";

        public static final String categoryInfo = "<w:p w:rsidR=\"00C749FF\" w:rsidRPr=\"00EB31EF\" w:rsidRDefault=\"00EB31EF\" w:rsidP=\"00C749FF\">\n" +
                "    <w:pPr>\n" +
                "        <w:spacing w:line=\"220\" w:lineRule=\"atLeast\"/>\n" +
                "        <w:rPr>\n" +
                "            <w:rFonts w:ascii=\"Times New Roman\" w:hAnsi=\"Times New Roman\" w:cs=\"Times New Roman\"/>\n" +
                "            <w:b/>\n" +
                "            <w:sz w:val=\"24\"/>\n" +
                "            <w:szCs w:val=\"24\"/>\n" +
                "        </w:rPr>\n" +
                "    </w:pPr>\n" +
                "    <w:r w:rsidRPr=\"00EB31EF\">\n" +
                "        <w:rPr>\n" +
                "            <w:rFonts w:ascii=\"Times New Roman\" w:hAnsi=\"Times New Roman\" w:cs=\"Times New Roman\"/>\n" +
                "            <w:b/>\n" +
                "            <w:sz w:val=\"24\"/>\n" +
                "            <w:szCs w:val=\"24\"/>\n" +
                "        </w:rPr>\n" +
                "        <w:t>" + categoryTitle + "</w:t>\n" +
                "    </w:r>\n" +
                "</w:p>\n";
    //服务信息
    public static final String serviceInfo = "<w:p w:rsidR=\"00C749FF\" w:rsidRPr=\"00EB31EF\" w:rsidRDefault=\"00EB31EF\" w:rsidP=\"00C749FF\">\n" +
            "    <w:pPr>\n" +
            "        <w:spacing w:line=\"220\" w:lineRule=\"atLeast\"/>\n" +
            "        <w:rPr>\n" +
            "            <w:rFonts w:ascii=\"Times New Roman\" w:hAnsi=\"Times New Roman\" w:cs=\"Times New Roman\"/>\n" +
            "            <w:b/>\n" +
            "            <w:sz w:val=\"24\"/>\n" +
            "            <w:szCs w:val=\"24\"/>\n" +
            "        </w:rPr>\n" +
            "    </w:pPr>\n" +
            "    <w:r w:rsidRPr=\"00EB31EF\">\n" +
            "        <w:rPr>\n" +
            "            <w:rFonts w:ascii=\"Times New Roman\" w:hAnsi=\"Times New Roman\" w:cs=\"Times New Roman\"/>\n" +
            "            <w:b/>\n" +
            "            <w:sz w:val=\"24\"/>\n" +
            "            <w:szCs w:val=\"24\"/>\n" +
            "        </w:rPr>\n" +
            "        <w:t>" + serviceTitle + "</w:t>\n" +
            "    </w:r>\n" +
            "</w:p>\n" +
            "<w:p w:rsidR=\"00C749FF\" w:rsidRPr=\"00EB31EF\" w:rsidRDefault=\"00C749FF\" w:rsidP=\"00C749FF\">\n" +
            "<w:pPr>\n" +
            "    <w:spacing w:line=\"220\" w:lineRule=\"atLeast\"/>\n" +
            "    <w:ind w:firstLine=\"720\"/>\n" +
            "    <w:rPr>\n" +
            "        <w:rFonts w:ascii=\"Times New Roman\" w:hAnsi=\"Times New Roman\" w:cs=\"Times New Roman\"/>\n" +
            "        <w:sz w:val=\"24\"/>\n" +
            "        <w:szCs w:val=\"24\"/>\n" +
            "    </w:rPr>\n" +
            "</w:pPr>\n" +
            "<w:r w:rsidRPr=\"00EB31EF\">\n" +
            "    <w:rPr>\n" +
            "        <w:rFonts w:ascii=\"Times New Roman\" w:hAnsi=\"Times New Roman\" w:cs=\"Times New Roman\"/>\n" +
            "        <w:sz w:val=\"24\"/>\n" +
            "        <w:szCs w:val=\"24\"/>\n" +
            "    </w:rPr>\n" +
            "    <w:t>功能描述：</w:t>\n" +
            "</w:r>\n" +
            "<w:r w:rsidR=\"00EB31EF\" w:rsidRPr=\"00EB31EF\">\n" +
            "    <w:rPr>\n" +
            "        <w:rFonts w:ascii=\"Times New Roman\" w:hAnsi=\"Times New Roman\" w:cs=\"Times New Roman\"/>\n" +
            "        <w:sz w:val=\"24\"/>\n" +
            "        <w:szCs w:val=\"24\"/>\n" +
            "    </w:rPr>\n" +
            "    <w:t xml:space=\"preserve\">" + serviceDesc + " </w:t>\n" +
            "</w:r>\n" +
            "</w:p>\n" +
            "<w:p w:rsidR=\"00C749FF\" w:rsidRPr=\"00EB31EF\" w:rsidRDefault=\"00C749FF\" w:rsidP=\"00C749FF\">\n" +
            "<w:pPr>\n" +
            "    <w:spacing w:line=\"220\" w:lineRule=\"atLeast\"/>\n" +
            "    <w:ind w:firstLine=\"720\"/>\n" +
            "    <w:rPr>\n" +
            "        <w:rFonts w:ascii=\"Times New Roman\" w:hAnsi=\"Times New Roman\" w:cs=\"Times New Roman\"/>\n" +
            "        <w:sz w:val=\"24\"/>\n" +
            "        <w:szCs w:val=\"24\"/>\n" +
            "    </w:rPr>\n" +
            "</w:pPr>\n" +
            "<w:r w:rsidRPr=\"00EB31EF\">\n" +
            "    <w:rPr>\n" +
            "        <w:rFonts w:ascii=\"Times New Roman\" w:hAnsi=\"Times New Roman\" w:cs=\"Times New Roman\"/>\n" +
            "        <w:sz w:val=\"24\"/>\n" +
            "        <w:szCs w:val=\"24\"/>\n" +
            "    </w:rPr>\n" +
            "    <w:t>本服务有以下场景：</w:t>\n" +
            "</w:r>\n" +
            "</w:p>\n";
    //场景信息
    public static final String operationInfo = "<w:p w:rsidR=\"00C749FF\" w:rsidRPr=\"00EB31EF\" w:rsidRDefault=\"00EB31EF\" w:rsidP=\"00C749FF\">\n" +
            "    <w:pPr>\n" +
            "        <w:spacing w:line=\"220\" w:lineRule=\"atLeast\"/>\n" +
            "        <w:ind w:firstLine=\"720\"/>\n" +
            "        <w:rPr>\n" +
            "            <w:rFonts w:ascii=\"Times New Roman\" w:hAnsi=\"Times New Roman\" w:cs=\"Times New Roman\"/>\n" +
            "            <w:b/>\n" +
            "            <w:sz w:val=\"24\"/>\n" +
            "            <w:szCs w:val=\"24\"/>\n" +
            "        </w:rPr>\n" +
            "    </w:pPr>\n" +
            "    <w:r w:rsidRPr=\"00EB31EF\">\n" +
            "        <w:rPr>\n" +
            "            <w:rFonts w:ascii=\"Times New Roman\" w:hAnsi=\"Times New Roman\" w:cs=\"Times New Roman\"/>\n" +
            "            <w:b/>\n" +
            "            <w:sz w:val=\"24\"/>\n" +
            "            <w:szCs w:val=\"24\"/>\n" +
            "        </w:rPr>\n" +
            "        <w:t>" + operationTitle + "</w:t>\n" +
            "    </w:r>\n" +
            "</w:p>\n" +
            "<w:p w:rsidR=\"00C749FF\" w:rsidRPr=\"00EB31EF\" w:rsidRDefault=\"00C749FF\" w:rsidP=\"00C749FF\">\n" +
            "<w:pPr>\n" +
            "    <w:spacing w:line=\"220\" w:lineRule=\"atLeast\"/>\n" +
            "    <w:ind w:firstLine=\"720\"/>\n" +
            "    <w:rPr>\n" +
            "        <w:rFonts w:ascii=\"Times New Roman\" w:hAnsi=\"Times New Roman\" w:cs=\"Times New Roman\"/>\n" +
            "        <w:sz w:val=\"24\"/>\n" +
            "        <w:szCs w:val=\"24\"/>\n" +
            "    </w:rPr>\n" +
            "</w:pPr>\n" +
            "<w:r w:rsidRPr=\"00EB31EF\">\n" +
            "    <w:rPr>\n" +
            "        <w:rFonts w:ascii=\"Times New Roman\" w:hAnsi=\"Times New Roman\" w:cs=\"Times New Roman\"/>\n" +
            "        <w:sz w:val=\"24\"/>\n" +
            "        <w:szCs w:val=\"24\"/>\n" +
            "    </w:rPr>\n" +
            "    <w:t>场景描述：</w:t>\n" +
            "</w:r>\n" +
            "<w:r w:rsidR=\"00EB31EF\" w:rsidRPr=\"00EB31EF\">\n" +
            "    <w:rPr>\n" +
            "        <w:rFonts w:ascii=\"Times New Roman\" w:hAnsi=\"Times New Roman\" w:cs=\"Times New Roman\"/>\n" +
            "        <w:sz w:val=\"24\"/>\n" +
            "        <w:szCs w:val=\"24\"/>\n" +
            "    </w:rPr>\n" +
            "    <w:t>" + operationDesc + "</w:t>\n" +
            "</w:r>\n" +
            "</w:p>\n" +
            "<w:p w:rsidR=\"00C749FF\" w:rsidRPr=\"00EB31EF\" w:rsidRDefault=\"00C749FF\" w:rsidP=\"00C749FF\">\n" +
            "<w:pPr>\n" +
            "    <w:spacing w:line=\"220\" w:lineRule=\"atLeast\"/>\n" +
            "    <w:ind w:firstLine=\"720\"/>\n" +
            "    <w:rPr>\n" +
            "        <w:rFonts w:ascii=\"Times New Roman\" w:hAnsi=\"Times New Roman\" w:cs=\"Times New Roman\"/>\n" +
            "        <w:sz w:val=\"24\"/>\n" +
            "        <w:szCs w:val=\"24\"/>\n" +
            "    </w:rPr>\n" +
            "</w:pPr>\n" +
            "<w:r w:rsidRPr=\"00EB31EF\">\n" +
            "    <w:rPr>\n" +
            "        <w:rFonts w:ascii=\"Times New Roman\" w:hAnsi=\"Times New Roman\" w:cs=\"Times New Roman\"/>\n" +
            "        <w:sz w:val=\"24\"/>\n" +
            "        <w:szCs w:val=\"24\"/>\n" +
            "    </w:rPr>\n" +
            "    <w:t>服务提供者：</w:t>\n" +
            "</w:r>\n" +
            "<w:r w:rsidR=\"00EB31EF\" w:rsidRPr=\"00EB31EF\">\n" +
            "    <w:rPr>\n" +
            "        <w:rFonts w:ascii=\"Times New Roman\" w:hAnsi=\"Times New Roman\" w:cs=\"Times New Roman\"/>\n" +
            "        <w:sz w:val=\"24\"/>\n" +
            "        <w:szCs w:val=\"24\"/>\n" +
            "    </w:rPr>\n" +
            "    <w:t>" + providers + "</w:t>\n" +
            "</w:r>\n" +
            "</w:p>\n" +
            "<w:p w:rsidR=\"00C749FF\" w:rsidRPr=\"00EB31EF\" w:rsidRDefault=\"00C749FF\" w:rsidP=\"00C749FF\">\n" +
            "<w:pPr>\n" +
            "    <w:spacing w:line=\"220\" w:lineRule=\"atLeast\"/>\n" +
            "    <w:ind w:firstLine=\"720\"/>\n" +
            "    <w:rPr>\n" +
            "        <w:rFonts w:ascii=\"Times New Roman\" w:hAnsi=\"Times New Roman\" w:cs=\"Times New Roman\"/>\n" +
            "        <w:sz w:val=\"24\"/>\n" +
            "        <w:szCs w:val=\"24\"/>\n" +
            "    </w:rPr>\n" +
            "</w:pPr>\n" +
            "<w:r w:rsidRPr=\"00EB31EF\">\n" +
            "    <w:rPr>\n" +
            "        <w:rFonts w:ascii=\"Times New Roman\" w:hAnsi=\"Times New Roman\" w:cs=\"Times New Roman\"/>\n" +
            "        <w:sz w:val=\"24\"/>\n" +
            "        <w:szCs w:val=\"24\"/>\n" +
            "    </w:rPr>\n" +
            "    <w:t>服务消费者：</w:t>\n" +
            "</w:r>\n" +
            "<w:r w:rsidR=\"00EB31EF\" w:rsidRPr=\"00EB31EF\">\n" +
            "    <w:rPr>\n" +
            "        <w:rFonts w:ascii=\"Times New Roman\" w:hAnsi=\"Times New Roman\" w:cs=\"Times New Roman\"/>\n" +
            "        <w:sz w:val=\"24\"/>\n" +
            "        <w:szCs w:val=\"24\"/>\n" +
            "    </w:rPr>\n" +
            "    <w:t>" + consumers + "</w:t>\n" +
            "</w:r>\n" +
            "</w:p>\n" +
            "<w:tbl>\n" +
            "<w:tblPr>\n" +
            "    <w:tblStyle w:val=\"a3\"/>\n" +
            "    <w:tblW w:w=\"0\" w:type=\"auto\"/>\n" +
            "    <w:tblLook w:val=\"04A0\"/>\n" +
            "</w:tblPr>\n" +
            "<w:tblGrid>\n" +
            "    <w:gridCol w:w=\"1217\"/>\n" +
            "    <w:gridCol w:w=\"1217\"/>\n" +
            "    <w:gridCol w:w=\"1217\"/>\n" +
            "    <w:gridCol w:w=\"1217\"/>\n" +
            "    <w:gridCol w:w=\"1218\"/>\n" +
            "    <w:gridCol w:w=\"1218\"/>\n" +
            "    <w:gridCol w:w=\"1218\"/>\n" +
            "</w:tblGrid>\n" +
            "<w:tr w:rsidR=\"00C749FF\" w:rsidRPr=\"00EB31EF\" w:rsidTr=\"00C749FF\">\n" +
            "    <w:tc>\n" +
            "        <w:tcPr>\n" +
            "            <w:tcW w:w=\"1217\" w:type=\"dxa\"/>\n" +
            "        </w:tcPr>\n" +
            "        <w:p w:rsidR=\"00C749FF\" w:rsidRPr=\"00EB31EF\" w:rsidRDefault=\"00C749FF\"\n" +
            "             w:rsidP=\"00C749FF\">\n" +
            "            <w:pPr>\n" +
            "                <w:spacing w:line=\"220\" w:lineRule=\"atLeast\"/>\n" +
            "                <w:rPr>\n" +
            "                    <w:rFonts w:ascii=\"Times New Roman\" w:hAnsi=\"Times New Roman\"\n" +
            "                              w:cs=\"Times New Roman\"/>\n" +
            "                    <w:b/>\n" +
            "                    <w:sz w:val=\"18\"/>\n" +
            "                    <w:szCs w:val=\"18\"/>\n" +
            "                </w:rPr>\n" +
            "            </w:pPr>\n" +
            "            <w:r w:rsidRPr=\"00EB31EF\">\n" +
            "                <w:rPr>\n" +
            "                    <w:rFonts w:ascii=\"Times New Roman\" w:hAnsi=\"Times New Roman\"\n" +
            "                              w:cs=\"Times New Roman\"/>\n" +
            "                    <w:b/>\n" +
            "                    <w:sz w:val=\"18\"/>\n" +
            "                    <w:szCs w:val=\"18\"/>\n" +
            "                </w:rPr>\n" +
            "                <w:t>序号</w:t>\n" +
            "            </w:r>\n" +
            "        </w:p>\n" +
            "    </w:tc>\n" +
            "    <w:tc>\n" +
            "        <w:tcPr>\n" +
            "            <w:tcW w:w=\"1217\" w:type=\"dxa\"/>\n" +
            "        </w:tcPr>\n" +
            "        <w:p w:rsidR=\"00C749FF\" w:rsidRPr=\"00EB31EF\" w:rsidRDefault=\"00C749FF\"\n" +
            "             w:rsidP=\"00C749FF\">\n" +
            "            <w:pPr>\n" +
            "                <w:spacing w:line=\"220\" w:lineRule=\"atLeast\"/>\n" +
            "                <w:rPr>\n" +
            "                    <w:rFonts w:ascii=\"Times New Roman\" w:hAnsi=\"Times New Roman\"\n" +
            "                              w:cs=\"Times New Roman\"/>\n" +
            "                    <w:b/>\n" +
            "                    <w:sz w:val=\"18\"/>\n" +
            "                    <w:szCs w:val=\"18\"/>\n" +
            "                </w:rPr>\n" +
            "            </w:pPr>\n" +
            "            <w:r w:rsidRPr=\"00EB31EF\">\n" +
            "                <w:rPr>\n" +
            "                    <w:rFonts w:ascii=\"Times New Roman\" w:hAnsi=\"Times New Roman\"\n" +
            "                              w:cs=\"Times New Roman\"/>\n" +
            "                    <w:b/>\n" +
            "                    <w:sz w:val=\"18\"/>\n" +
            "                    <w:szCs w:val=\"18\"/>\n" +
            "                </w:rPr>\n" +
            "                <w:t>字段名称</w:t>\n" +
            "            </w:r>\n" +
            "        </w:p>\n" +
            "    </w:tc>\n" +
            "    <w:tc>\n" +
            "        <w:tcPr>\n" +
            "            <w:tcW w:w=\"1217\" w:type=\"dxa\"/>\n" +
            "        </w:tcPr>\n" +
            "        <w:p w:rsidR=\"00C749FF\" w:rsidRPr=\"00EB31EF\" w:rsidRDefault=\"00C749FF\"\n" +
            "             w:rsidP=\"00C749FF\">\n" +
            "            <w:pPr>\n" +
            "                <w:spacing w:line=\"220\" w:lineRule=\"atLeast\"/>\n" +
            "                <w:rPr>\n" +
            "                    <w:rFonts w:ascii=\"Times New Roman\" w:hAnsi=\"Times New Roman\"\n" +
            "                              w:cs=\"Times New Roman\"/>\n" +
            "                    <w:b/>\n" +
            "                    <w:sz w:val=\"18\"/>\n" +
            "                    <w:szCs w:val=\"18\"/>\n" +
            "                </w:rPr>\n" +
            "            </w:pPr>\n" +
            "            <w:r w:rsidRPr=\"00EB31EF\">\n" +
            "                <w:rPr>\n" +
            "                    <w:rFonts w:ascii=\"Times New Roman\" w:hAnsi=\"Times New Roman\"\n" +
            "                              w:cs=\"Times New Roman\"/>\n" +
            "                    <w:b/>\n" +
            "                    <w:sz w:val=\"18\"/>\n" +
            "                    <w:szCs w:val=\"18\"/>\n" +
            "                </w:rPr>\n" +
            "                <w:t>字段类型</w:t>\n" +
            "            </w:r>\n" +
            "        </w:p>\n" +
            "    </w:tc>\n" +
            "    <w:tc>\n" +
            "        <w:tcPr>\n" +
            "            <w:tcW w:w=\"1217\" w:type=\"dxa\"/>\n" +
            "        </w:tcPr>\n" +
            "        <w:p w:rsidR=\"00C749FF\" w:rsidRPr=\"00EB31EF\" w:rsidRDefault=\"00C749FF\"\n" +
            "             w:rsidP=\"00C749FF\">\n" +
            "            <w:pPr>\n" +
            "                <w:spacing w:line=\"220\" w:lineRule=\"atLeast\"/>\n" +
            "                <w:rPr>\n" +
            "                    <w:rFonts w:ascii=\"Times New Roman\" w:hAnsi=\"Times New Roman\"\n" +
            "                              w:cs=\"Times New Roman\"/>\n" +
            "                    <w:b/>\n" +
            "                    <w:sz w:val=\"18\"/>\n" +
            "                    <w:szCs w:val=\"18\"/>\n" +
            "                </w:rPr>\n" +
            "            </w:pPr>\n" +
            "            <w:r w:rsidRPr=\"00EB31EF\">\n" +
            "                <w:rPr>\n" +
            "                    <w:rFonts w:ascii=\"Times New Roman\" w:hAnsi=\"Times New Roman\"\n" +
            "                              w:cs=\"Times New Roman\"/>\n" +
            "                    <w:b/>\n" +
            "                    <w:sz w:val=\"18\"/>\n" +
            "                    <w:szCs w:val=\"18\"/>\n" +
            "                </w:rPr>\n" +
            "                <w:t>字段说明</w:t>\n" +
            "            </w:r>\n" +
            "        </w:p>\n" +
            "    </w:tc>\n" +
            "    <w:tc>\n" +
            "        <w:tcPr>\n" +
            "            <w:tcW w:w=\"1218\" w:type=\"dxa\"/>\n" +
            "        </w:tcPr>\n" +
            "        <w:p w:rsidR=\"00C749FF\" w:rsidRPr=\"00EB31EF\" w:rsidRDefault=\"00C749FF\"\n" +
            "             w:rsidP=\"00C749FF\">\n" +
            "            <w:pPr>\n" +
            "                <w:spacing w:line=\"220\" w:lineRule=\"atLeast\"/>\n" +
            "                <w:rPr>\n" +
            "                    <w:rFonts w:ascii=\"Times New Roman\" w:hAnsi=\"Times New Roman\"\n" +
            "                              w:cs=\"Times New Roman\"/>\n" +
            "                    <w:b/>\n" +
            "                    <w:sz w:val=\"18\"/>\n" +
            "                    <w:szCs w:val=\"18\"/>\n" +
            "                </w:rPr>\n" +
            "            </w:pPr>\n" +
            "            <w:r w:rsidRPr=\"00EB31EF\">\n" +
            "                <w:rPr>\n" +
            "                    <w:rFonts w:ascii=\"Times New Roman\" w:hAnsi=\"Times New Roman\"\n" +
            "                              w:cs=\"Times New Roman\"/>\n" +
            "                    <w:b/>\n" +
            "                    <w:sz w:val=\"18\"/>\n" +
            "                    <w:szCs w:val=\"18\"/>\n" +
            "                </w:rPr>\n" +
            "                <w:t>是否必输</w:t>\n" +
            "            </w:r>\n" +
            "        </w:p>\n" +
            "    </w:tc>\n" +
            "    <w:tc>\n" +
            "        <w:tcPr>\n" +
            "            <w:tcW w:w=\"1218\" w:type=\"dxa\"/>\n" +
            "        </w:tcPr>\n" +
            "        <w:p w:rsidR=\"00C749FF\" w:rsidRPr=\"00EB31EF\" w:rsidRDefault=\"00C749FF\"\n" +
            "             w:rsidP=\"00C749FF\">\n" +
            "            <w:pPr>\n" +
            "                <w:spacing w:line=\"220\" w:lineRule=\"atLeast\"/>\n" +
            "                <w:rPr>\n" +
            "                    <w:rFonts w:ascii=\"Times New Roman\" w:hAnsi=\"Times New Roman\"\n" +
            "                              w:cs=\"Times New Roman\"/>\n" +
            "                    <w:b/>\n" +
            "                    <w:sz w:val=\"18\"/>\n" +
            "                    <w:szCs w:val=\"18\"/>\n" +
            "                </w:rPr>\n" +
            "            </w:pPr>\n" +
            "            <w:r w:rsidRPr=\"00EB31EF\">\n" +
            "                <w:rPr>\n" +
            "                    <w:rFonts w:ascii=\"Times New Roman\" w:hAnsi=\"Times New Roman\"\n" +
            "                              w:cs=\"Times New Roman\"/>\n" +
            "                    <w:b/>\n" +
            "                    <w:sz w:val=\"18\"/>\n" +
            "                    <w:szCs w:val=\"18\"/>\n" +
            "                </w:rPr>\n" +
            "                <w:t>约束条件</w:t>\n" +
            "            </w:r>\n" +
            "        </w:p>\n" +
            "    </w:tc>\n" +
            "    <w:tc>\n" +
            "        <w:tcPr>\n" +
            "            <w:tcW w:w=\"1218\" w:type=\"dxa\"/>\n" +
            "        </w:tcPr>\n" +
            "        <w:p w:rsidR=\"00C749FF\" w:rsidRPr=\"00EB31EF\" w:rsidRDefault=\"00C749FF\"\n" +
            "             w:rsidP=\"00C749FF\">\n" +
            "            <w:pPr>\n" +
            "                <w:spacing w:line=\"220\" w:lineRule=\"atLeast\"/>\n" +
            "                <w:rPr>\n" +
            "                    <w:rFonts w:ascii=\"Times New Roman\" w:hAnsi=\"Times New Roman\"\n" +
            "                              w:cs=\"Times New Roman\"/>\n" +
            "                    <w:b/>\n" +
            "                    <w:sz w:val=\"18\"/>\n" +
            "                    <w:szCs w:val=\"18\"/>\n" +
            "                </w:rPr>\n" +
            "            </w:pPr>\n" +
            "            <w:r w:rsidRPr=\"00EB31EF\">\n" +
            "                <w:rPr>\n" +
            "                    <w:rFonts w:ascii=\"Times New Roman\" w:hAnsi=\"Times New Roman\"\n" +
            "                              w:cs=\"Times New Roman\"/>\n" +
            "                    <w:b/>\n" +
            "                    <w:sz w:val=\"18\"/>\n" +
            "                    <w:szCs w:val=\"18\"/>\n" +
            "                </w:rPr>\n" +
            "                <w:t>备注</w:t>\n" +
            "            </w:r>\n" +
            "        </w:p>\n" +
            "    </w:tc>\n" +
            "</w:tr>\n" +
            "<w:tr w:rsidR=\"00C749FF\" w:rsidRPr=\"00EB31EF\" w:rsidTr=\"00CE3E8F\">\n" +
            "    <w:tc>\n" +
            "        <w:tcPr>\n" +
            "            <w:tcW w:w=\"8522\" w:type=\"dxa\"/>\n" +
            "            <w:gridSpan w:val=\"7\"/>\n" +
            "            <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"FF5050\"/>\n" +
            "        </w:tcPr>\n" +
            "        <w:p w:rsidR=\"00C749FF\" w:rsidRPr=\"00EB31EF\" w:rsidRDefault=\"00C749FF\"\n" +
            "             w:rsidP=\"00C749FF\">\n" +
            "            <w:pPr>\n" +
            "                <w:spacing w:line=\"220\" w:lineRule=\"atLeast\"/>\n" +
            "                <w:jc w:val=\"center\"/>\n" +
            "                <w:rPr>\n" +
            "                    <w:rFonts w:ascii=\"Times New Roman\" w:hAnsi=\"Times New Roman\"\n" +
            "                              w:cs=\"Times New Roman\"/>\n" +
            "                    <w:b/>\n" +
            "                    <w:sz w:val=\"18\"/>\n" +
            "                    <w:szCs w:val=\"18\"/>\n" +
            "                </w:rPr>\n" +
            "            </w:pPr>\n" +
            "            <w:r w:rsidRPr=\"00EB31EF\">\n" +
            "                <w:rPr>\n" +
            "                    <w:rFonts w:ascii=\"Times New Roman\" w:hAnsi=\"Times New Roman\"\n" +
            "                              w:cs=\"Times New Roman\"/>\n" +
            "                    <w:b/>\n" +
            "                    <w:sz w:val=\"18\"/>\n" +
            "                    <w:szCs w:val=\"18\"/>\n" +
            "                </w:rPr>\n" +
            "                <w:t>输入</w:t>\n" +
            "            </w:r>\n" +
            "        </w:p>\n" +
            "    </w:tc>\n" +
            "</w:tr>\n";
    public static final String sdaArray = "<w:tr w:rsidR=\"00C749FF\" w:rsidRPr=\"00EB31EF\" w:rsidTr=\"00CE3E8F\">\n" +
            "    <w:tc>\n" +
            "        <w:tcPr>\n" +
            "            <w:tcW w:w=\"1217\" w:type=\"dxa\"/>\n" +
            "            <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"FFFF00\"/>\n" +
            "        </w:tcPr>\n" +
            "        <w:p w:rsidR=\"00C749FF\" w:rsidRPr=\"00EB31EF\" w:rsidRDefault=\"00EB31EF\"\n" +
            "             w:rsidP=\"00C749FF\">\n" +
            "            <w:pPr>\n" +
            "                <w:spacing w:line=\"220\" w:lineRule=\"atLeast\"/>\n" +
            "                <w:rPr>\n" +
            "                    <w:rFonts w:ascii=\"Times New Roman\" w:hAnsi=\"Times New Roman\"\n" +
            "                              w:cs=\"Times New Roman\"/>\n" +
            "                    <w:sz w:val=\"18\"/>\n" +
            "                    <w:szCs w:val=\"18\"/>\n" +
            "                </w:rPr>\n" +
            "            </w:pPr>\n" +
            "            <w:r w:rsidRPr=\"00EB31EF\">\n" +
            "                <w:rPr>\n" +
            "                    <w:rFonts w:ascii=\"Times New Roman\" w:hAnsi=\"Times New Roman\"\n" +
            "                              w:cs=\"Times New Roman\"/>\n" +
            "                    <w:sz w:val=\"18\"/>\n" +
            "                    <w:szCs w:val=\"18\"/>\n" +
            "                </w:rPr>\n" +
            "                <w:t>" + reqSeq + "</w:t>\n" +
            "            </w:r>\n" +
            "        </w:p>\n" +
            "    </w:tc>\n" +
            "    <w:tc>\n" +
            "        <w:tcPr>\n" +
            "            <w:tcW w:w=\"1217\" w:type=\"dxa\"/>\n" +
            "            <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"FFFF00\"/>\n" +
            "        </w:tcPr>\n" +
            "        <w:p w:rsidR=\"00C749FF\" w:rsidRPr=\"00EB31EF\" w:rsidRDefault=\"00EB31EF\"\n" +
            "             w:rsidP=\"00C749FF\">\n" +
            "            <w:pPr>\n" +
            "                <w:spacing w:line=\"220\" w:lineRule=\"atLeast\"/>\n" +
            "                <w:rPr>\n" +
            "                    <w:rFonts w:ascii=\"Times New Roman\" w:hAnsi=\"Times New Roman\"\n" +
            "                              w:cs=\"Times New Roman\"/>\n" +
            "                    <w:sz w:val=\"18\"/>\n" +
            "                    <w:szCs w:val=\"18\"/>\n" +
            "                </w:rPr>\n" +
            "            </w:pPr>\n" +
            "            <w:r w:rsidRPr=\"00EB31EF\">\n" +
            "                <w:rPr>\n" +
            "                    <w:rFonts w:ascii=\"Times New Roman\" w:hAnsi=\"Times New Roman\"\n" +
            "                              w:cs=\"Times New Roman\"/>\n" +
            "                    <w:sz w:val=\"18\"/>\n" +
            "                    <w:szCs w:val=\"18\"/>\n" +
            "                </w:rPr>\n" +
            "                <w:t>" + reqStructName + "</w:t>\n" +
            "            </w:r>\n" +
            "        </w:p>\n" +
            "    </w:tc>\n" +
            "    <w:tc>\n" +
            "        <w:tcPr>\n" +
            "            <w:tcW w:w=\"1217\" w:type=\"dxa\"/>\n" +
            "            <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"FFFF00\"/>\n" +
            "        </w:tcPr>\n" +
            "        <w:p w:rsidR=\"00C749FF\" w:rsidRPr=\"00EB31EF\" w:rsidRDefault=\"00EB31EF\"\n" +
            "             w:rsidP=\"00EB31EF\">\n" +
            "            <w:pPr>\n" +
            "                <w:spacing w:line=\"220\" w:lineRule=\"atLeast\"/>\n" +
            "                <w:rPr>\n" +
            "                    <w:rFonts w:ascii=\"Times New Roman\" w:hAnsi=\"Times New Roman\"\n" +
            "                              w:cs=\"Times New Roman\"/>\n" +
            "                    <w:sz w:val=\"18\"/>\n" +
            "                    <w:szCs w:val=\"18\"/>\n" +
            "                </w:rPr>\n" +
            "            </w:pPr>\n" +
            "            <w:r w:rsidRPr=\"00EB31EF\">\n" +
            "                <w:rPr>\n" +
            "                    <w:rFonts w:ascii=\"Times New Roman\" w:hAnsi=\"Times New Roman\"\n" +
            "                              w:cs=\"Times New Roman\"/>\n" +
            "                    <w:sz w:val=\"18\"/>\n" +
            "                    <w:szCs w:val=\"18\"/>\n" +
            "                </w:rPr>\n" +
            "                <w:t>" + reqType + "</w:t>\n" +
            "            </w:r>\n" +
            "        </w:p>\n" +
            "    </w:tc>\n" +
            "    <w:tc>\n" +
            "        <w:tcPr>\n" +
            "            <w:tcW w:w=\"1217\" w:type=\"dxa\"/>\n" +
            "            <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"FFFF00\"/>\n" +
            "        </w:tcPr>\n" +
            "        <w:p w:rsidR=\"00C749FF\" w:rsidRPr=\"00EB31EF\" w:rsidRDefault=\"00EB31EF\"\n" +
            "             w:rsidP=\"00EB31EF\">\n" +
            "            <w:pPr>\n" +
            "                <w:spacing w:line=\"220\" w:lineRule=\"atLeast\"/>\n" +
            "                <w:rPr>\n" +
            "                    <w:rFonts w:ascii=\"Times New Roman\" w:hAnsi=\"Times New Roman\"\n" +
            "                              w:cs=\"Times New Roman\"/>\n" +
            "                    <w:sz w:val=\"18\"/>\n" +
            "                    <w:szCs w:val=\"18\"/>\n" +
            "                </w:rPr>\n" +
            "            </w:pPr>\n" +
            "            <w:r w:rsidRPr=\"00EB31EF\">\n" +
            "                <w:rPr>\n" +
            "                    <w:rFonts w:ascii=\"Times New Roman\" w:hAnsi=\"Times New Roman\"\n" +
            "                              w:cs=\"Times New Roman\"/>\n" +
            "                    <w:sz w:val=\"18\"/>\n" +
            "                    <w:szCs w:val=\"18\"/>\n" +
            "                </w:rPr>\n" +
            "                <w:t>" + reqStructAlias + "</w:t>\n" +
            "            </w:r>\n" +
            "        </w:p>\n" +
            "    </w:tc>\n" +
            "    <w:tc>\n" +
            "        <w:tcPr>\n" +
            "            <w:tcW w:w=\"1218\" w:type=\"dxa\"/>\n" +
            "            <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"FFFF00\"/>\n" +
            "        </w:tcPr>\n" +
            "        <w:p w:rsidR=\"00C749FF\" w:rsidRPr=\"00EB31EF\" w:rsidRDefault=\"00EB31EF\"\n" +
            "             w:rsidP=\"00EB31EF\">\n" +
            "            <w:pPr>\n" +
            "                <w:spacing w:line=\"220\" w:lineRule=\"atLeast\"/>\n" +
            "                <w:rPr>\n" +
            "                    <w:rFonts w:ascii=\"Times New Roman\" w:hAnsi=\"Times New Roman\"\n" +
            "                              w:cs=\"Times New Roman\"/>\n" +
            "                    <w:sz w:val=\"18\"/>\n" +
            "                    <w:szCs w:val=\"18\"/>\n" +
            "                </w:rPr>\n" +
            "            </w:pPr>\n" +
            "            <w:r w:rsidRPr=\"00EB31EF\">\n" +
            "                <w:rPr>\n" +
            "                    <w:rFonts w:ascii=\"Times New Roman\" w:hAnsi=\"Times New Roman\"\n" +
            "                              w:cs=\"Times New Roman\"/>\n" +
            "                    <w:sz w:val=\"18\"/>\n" +
            "                    <w:szCs w:val=\"18\"/>\n" +
            "                </w:rPr>\n" +
            "                <w:t>" + reqRequired + "</w:t>\n" +
            "            </w:r>\n" +
            "        </w:p>\n" +
            "    </w:tc>\n" +
            "    <w:tc>\n" +
            "        <w:tcPr>\n" +
            "            <w:tcW w:w=\"1218\" w:type=\"dxa\"/>\n" +
            "            <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"FFFF00\"/>\n" +
            "        </w:tcPr>\n" +
            "        <w:p w:rsidR=\"00C749FF\" w:rsidRPr=\"00EB31EF\" w:rsidRDefault=\"00EB31EF\"\n" +
            "             w:rsidP=\"00EB31EF\">\n" +
            "            <w:pPr>\n" +
            "                <w:spacing w:line=\"220\" w:lineRule=\"atLeast\"/>\n" +
            "                <w:rPr>\n" +
            "                    <w:rFonts w:ascii=\"Times New Roman\" w:hAnsi=\"Times New Roman\"\n" +
            "                              w:cs=\"Times New Roman\"/>\n" +
            "                    <w:sz w:val=\"18\"/>\n" +
            "                    <w:szCs w:val=\"18\"/>\n" +
            "                </w:rPr>\n" +
            "            </w:pPr>\n" +
            "            <w:r w:rsidRPr=\"00EB31EF\">\n" +
            "                <w:rPr>\n" +
            "                    <w:rFonts w:ascii=\"Times New Roman\" w:hAnsi=\"Times New Roman\"\n" +
            "                              w:cs=\"Times New Roman\"/>\n" +
            "                    <w:sz w:val=\"18\"/>\n" +
            "                    <w:szCs w:val=\"18\"/>\n" +
            "                </w:rPr>\n" +
            "                <w:t>" + reqConstraint + "</w:t>\n" +
            "            </w:r>\n" +
            "        </w:p>\n" +
            "    </w:tc>\n" +
            "    <w:tc>\n" +
            "        <w:tcPr>\n" +
            "            <w:tcW w:w=\"1218\" w:type=\"dxa\"/>\n" +
            "            <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"FFFF00\"/>\n" +
            "        </w:tcPr>\n" +
            "        <w:p w:rsidR=\"00C749FF\" w:rsidRPr=\"00EB31EF\" w:rsidRDefault=\"00EB31EF\"\n" +
            "             w:rsidP=\"00EB31EF\">\n" +
            "            <w:pPr>\n" +
            "                <w:spacing w:line=\"220\" w:lineRule=\"atLeast\"/>\n" +
            "                <w:rPr>\n" +
            "                    <w:rFonts w:ascii=\"Times New Roman\" w:hAnsi=\"Times New Roman\"\n" +
            "                              w:cs=\"Times New Roman\"/>\n" +
            "                    <w:sz w:val=\"18\"/>\n" +
            "                    <w:szCs w:val=\"18\"/>\n" +
            "                </w:rPr>\n" +
            "            </w:pPr>\n" +
            "            <w:r w:rsidRPr=\"00EB31EF\">\n" +
            "                <w:rPr>\n" +
            "                    <w:rFonts w:ascii=\"Times New Roman\" w:hAnsi=\"Times New Roman\"\n" +
            "                              w:cs=\"Times New Roman\"/>\n" +
            "                    <w:sz w:val=\"18\"/>\n" +
            "                    <w:szCs w:val=\"18\"/>\n" +
            "                </w:rPr>\n" +
            "                <w:t>" + reqRemark + "</w:t>\n" +
            "            </w:r>\n" +
            "        </w:p>\n" +
            "    </w:tc>\n" +
            "</w:tr>\n";
    public static final String sdaReq = "<w:tr w:rsidR=\"00C749FF\" w:rsidRPr=\"00EB31EF\" w:rsidTr=\"00CE3E8F\">\n" +
            "    <w:tc>\n" +
            "        <w:tcPr>\n" +
            "            <w:tcW w:w=\"1217\" w:type=\"dxa\"/>\n" +
            "            <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"FF5050\"/>\n" +
            "        </w:tcPr>\n" +
            "        <w:p w:rsidR=\"00C749FF\" w:rsidRPr=\"00EB31EF\" w:rsidRDefault=\"00EB31EF\"\n" +
            "             w:rsidP=\"00C749FF\">\n" +
            "            <w:pPr>\n" +
            "                <w:spacing w:line=\"220\" w:lineRule=\"atLeast\"/>\n" +
            "                <w:rPr>\n" +
            "                    <w:rFonts w:ascii=\"Times New Roman\" w:hAnsi=\"Times New Roman\"\n" +
            "                              w:cs=\"Times New Roman\"/>\n" +
            "                    <w:sz w:val=\"18\"/>\n" +
            "                    <w:szCs w:val=\"18\"/>\n" +
            "                </w:rPr>\n" +
            "            </w:pPr>\n" +
            "            <w:r w:rsidRPr=\"00EB31EF\">\n" +
            "                <w:rPr>\n" +
            "                    <w:rFonts w:ascii=\"Times New Roman\" w:hAnsi=\"Times New Roman\"\n" +
            "                              w:cs=\"Times New Roman\"/>\n" +
            "                    <w:sz w:val=\"18\"/>\n" +
            "                    <w:szCs w:val=\"18\"/>\n" +
            "                </w:rPr>\n" +
            "                <w:t>" + reqSeq + "</w:t>\n" +
            "            </w:r>\n" +
            "        </w:p>\n" +
            "    </w:tc>\n" +
            "    <w:tc>\n" +
            "        <w:tcPr>\n" +
            "            <w:tcW w:w=\"1217\" w:type=\"dxa\"/>\n" +
            "            <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"FF5050\"/>\n" +
            "        </w:tcPr>\n" +
            "        <w:p w:rsidR=\"00C749FF\" w:rsidRPr=\"00EB31EF\" w:rsidRDefault=\"00EB31EF\"\n" +
            "             w:rsidP=\"00C749FF\">\n" +
            "            <w:pPr>\n" +
            "                <w:spacing w:line=\"220\" w:lineRule=\"atLeast\"/>\n" +
            "                <w:rPr>\n" +
            "                    <w:rFonts w:ascii=\"Times New Roman\" w:hAnsi=\"Times New Roman\"\n" +
            "                              w:cs=\"Times New Roman\"/>\n" +
            "                    <w:sz w:val=\"18\"/>\n" +
            "                    <w:szCs w:val=\"18\"/>\n" +
            "                </w:rPr>\n" +
            "            </w:pPr>\n" +
            "            <w:r w:rsidRPr=\"00EB31EF\">\n" +
            "                <w:rPr>\n" +
            "                    <w:rFonts w:ascii=\"Times New Roman\" w:hAnsi=\"Times New Roman\"\n" +
            "                              w:cs=\"Times New Roman\"/>\n" +
            "                    <w:sz w:val=\"18\"/>\n" +
            "                    <w:szCs w:val=\"18\"/>\n" +
            "                </w:rPr>\n" +
            "                <w:t>" + reqStructName + "</w:t>\n" +
            "            </w:r>\n" +
            "        </w:p>\n" +
            "    </w:tc>\n" +
            "    <w:tc>\n" +
            "        <w:tcPr>\n" +
            "            <w:tcW w:w=\"1217\" w:type=\"dxa\"/>\n" +
            "            <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"FF5050\"/>\n" +
            "        </w:tcPr>\n" +
            "        <w:p w:rsidR=\"00C749FF\" w:rsidRPr=\"00EB31EF\" w:rsidRDefault=\"00EB31EF\"\n" +
            "             w:rsidP=\"00EB31EF\">\n" +
            "            <w:pPr>\n" +
            "                <w:spacing w:line=\"220\" w:lineRule=\"atLeast\"/>\n" +
            "                <w:rPr>\n" +
            "                    <w:rFonts w:ascii=\"Times New Roman\" w:hAnsi=\"Times New Roman\"\n" +
            "                              w:cs=\"Times New Roman\"/>\n" +
            "                    <w:sz w:val=\"18\"/>\n" +
            "                    <w:szCs w:val=\"18\"/>\n" +
            "                </w:rPr>\n" +
            "            </w:pPr>\n" +
            "            <w:r w:rsidRPr=\"00EB31EF\">\n" +
            "                <w:rPr>\n" +
            "                    <w:rFonts w:ascii=\"Times New Roman\" w:hAnsi=\"Times New Roman\"\n" +
            "                              w:cs=\"Times New Roman\"/>\n" +
            "                    <w:sz w:val=\"18\"/>\n" +
            "                    <w:szCs w:val=\"18\"/>\n" +
            "                </w:rPr>\n" +
            "                <w:t>" + reqType + "</w:t>\n" +
            "            </w:r>\n" +
            "        </w:p>\n" +
            "    </w:tc>\n" +
            "    <w:tc>\n" +
            "        <w:tcPr>\n" +
            "            <w:tcW w:w=\"1217\" w:type=\"dxa\"/>\n" +
            "            <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"FF5050\"/>\n" +
            "        </w:tcPr>\n" +
            "        <w:p w:rsidR=\"00C749FF\" w:rsidRPr=\"00EB31EF\" w:rsidRDefault=\"00EB31EF\"\n" +
            "             w:rsidP=\"00EB31EF\">\n" +
            "            <w:pPr>\n" +
            "                <w:spacing w:line=\"220\" w:lineRule=\"atLeast\"/>\n" +
            "                <w:rPr>\n" +
            "                    <w:rFonts w:ascii=\"Times New Roman\" w:hAnsi=\"Times New Roman\"\n" +
            "                              w:cs=\"Times New Roman\"/>\n" +
            "                    <w:sz w:val=\"18\"/>\n" +
            "                    <w:szCs w:val=\"18\"/>\n" +
            "                </w:rPr>\n" +
            "            </w:pPr>\n" +
            "            <w:r w:rsidRPr=\"00EB31EF\">\n" +
            "                <w:rPr>\n" +
            "                    <w:rFonts w:ascii=\"Times New Roman\" w:hAnsi=\"Times New Roman\"\n" +
            "                              w:cs=\"Times New Roman\"/>\n" +
            "                    <w:sz w:val=\"18\"/>\n" +
            "                    <w:szCs w:val=\"18\"/>\n" +
            "                </w:rPr>\n" +
            "                <w:t>" + reqStructAlias + "</w:t>\n" +
            "            </w:r>\n" +
            "        </w:p>\n" +
            "    </w:tc>\n" +
            "    <w:tc>\n" +
            "        <w:tcPr>\n" +
            "            <w:tcW w:w=\"1218\" w:type=\"dxa\"/>\n" +
            "            <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"FF5050\"/>\n" +
            "        </w:tcPr>\n" +
            "        <w:p w:rsidR=\"00C749FF\" w:rsidRPr=\"00EB31EF\" w:rsidRDefault=\"00EB31EF\"\n" +
            "             w:rsidP=\"00EB31EF\">\n" +
            "            <w:pPr>\n" +
            "                <w:spacing w:line=\"220\" w:lineRule=\"atLeast\"/>\n" +
            "                <w:rPr>\n" +
            "                    <w:rFonts w:ascii=\"Times New Roman\" w:hAnsi=\"Times New Roman\"\n" +
            "                              w:cs=\"Times New Roman\"/>\n" +
            "                    <w:sz w:val=\"18\"/>\n" +
            "                    <w:szCs w:val=\"18\"/>\n" +
            "                </w:rPr>\n" +
            "            </w:pPr>\n" +
            "            <w:r w:rsidRPr=\"00EB31EF\">\n" +
            "                <w:rPr>\n" +
            "                    <w:rFonts w:ascii=\"Times New Roman\" w:hAnsi=\"Times New Roman\"\n" +
            "                              w:cs=\"Times New Roman\"/>\n" +
            "                    <w:sz w:val=\"18\"/>\n" +
            "                    <w:szCs w:val=\"18\"/>\n" +
            "                </w:rPr>\n" +
            "                <w:t>" + reqRequired + "</w:t>\n" +
            "            </w:r>\n" +
            "        </w:p>\n" +
            "    </w:tc>\n" +
            "    <w:tc>\n" +
            "        <w:tcPr>\n" +
            "            <w:tcW w:w=\"1218\" w:type=\"dxa\"/>\n" +
            "            <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"FF5050\"/>\n" +
            "        </w:tcPr>\n" +
            "        <w:p w:rsidR=\"00C749FF\" w:rsidRPr=\"00EB31EF\" w:rsidRDefault=\"00EB31EF\"\n" +
            "             w:rsidP=\"00EB31EF\">\n" +
            "            <w:pPr>\n" +
            "                <w:spacing w:line=\"220\" w:lineRule=\"atLeast\"/>\n" +
            "                <w:rPr>\n" +
            "                    <w:rFonts w:ascii=\"Times New Roman\" w:hAnsi=\"Times New Roman\"\n" +
            "                              w:cs=\"Times New Roman\"/>\n" +
            "                    <w:sz w:val=\"18\"/>\n" +
            "                    <w:szCs w:val=\"18\"/>\n" +
            "                </w:rPr>\n" +
            "            </w:pPr>\n" +
            "            <w:r w:rsidRPr=\"00EB31EF\">\n" +
            "                <w:rPr>\n" +
            "                    <w:rFonts w:ascii=\"Times New Roman\" w:hAnsi=\"Times New Roman\"\n" +
            "                              w:cs=\"Times New Roman\"/>\n" +
            "                    <w:sz w:val=\"18\"/>\n" +
            "                    <w:szCs w:val=\"18\"/>\n" +
            "                </w:rPr>\n" +
            "                <w:t>" + reqConstraint + "</w:t>\n" +
            "            </w:r>\n" +
            "        </w:p>\n" +
            "    </w:tc>\n" +
            "    <w:tc>\n" +
            "        <w:tcPr>\n" +
            "            <w:tcW w:w=\"1218\" w:type=\"dxa\"/>\n" +
            "            <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"FF5050\"/>\n" +
            "        </w:tcPr>\n" +
            "        <w:p w:rsidR=\"00C749FF\" w:rsidRPr=\"00EB31EF\" w:rsidRDefault=\"00EB31EF\"\n" +
            "             w:rsidP=\"00EB31EF\">\n" +
            "            <w:pPr>\n" +
            "                <w:spacing w:line=\"220\" w:lineRule=\"atLeast\"/>\n" +
            "                <w:rPr>\n" +
            "                    <w:rFonts w:ascii=\"Times New Roman\" w:hAnsi=\"Times New Roman\"\n" +
            "                              w:cs=\"Times New Roman\"/>\n" +
            "                    <w:sz w:val=\"18\"/>\n" +
            "                    <w:szCs w:val=\"18\"/>\n" +
            "                </w:rPr>\n" +
            "            </w:pPr>\n" +
            "            <w:r w:rsidRPr=\"00EB31EF\">\n" +
            "                <w:rPr>\n" +
            "                    <w:rFonts w:ascii=\"Times New Roman\" w:hAnsi=\"Times New Roman\"\n" +
            "                              w:cs=\"Times New Roman\"/>\n" +
            "                    <w:sz w:val=\"18\"/>\n" +
            "                    <w:szCs w:val=\"18\"/>\n" +
            "                </w:rPr>\n" +
            "                <w:t>" + reqRemark + "</w:t>\n" +
            "            </w:r>\n" +
            "        </w:p>\n" +
            "    </w:tc>\n" +
            "</w:tr>\n";
    public static final String sdaSeperator = "<w:tr w:rsidR=\"00CE3E8F\" w:rsidRPr=\"00EB31EF\" w:rsidTr=\"00CE3E8F\">\n" +
            "    <w:tc>\n" +
            "        <w:tcPr>\n" +
            "            <w:tcW w:w=\"8522\" w:type=\"dxa\"/>\n" +
            "            <w:gridSpan w:val=\"7\"/>\n" +
            "            <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"FFFF00\"/>\n" +
            "        </w:tcPr>\n" +
            "        <w:p w:rsidR=\"00CE3E8F\" w:rsidRPr=\"00EB31EF\" w:rsidRDefault=\"00CE3E8F\"\n" +
            "             w:rsidP=\"00CE3E8F\">\n" +
            "            <w:pPr>\n" +
            "                <w:spacing w:line=\"220\" w:lineRule=\"atLeast\"/>\n" +
            "                <w:jc w:val=\"center\"/>\n" +
            "                <w:rPr>\n" +
            "                    <w:rFonts w:ascii=\"Times New Roman\" w:hAnsi=\"Times New Roman\"\n" +
            "                              w:cs=\"Times New Roman\"/>\n" +
            "                    <w:b/>\n" +
            "                    <w:sz w:val=\"18\"/>\n" +
            "                    <w:szCs w:val=\"18\"/>\n" +
            "                </w:rPr>\n" +
            "            </w:pPr>\n" +
            "            <w:r w:rsidRPr=\"00EB31EF\">\n" +
            "                <w:rPr>\n" +
            "                    <w:rFonts w:ascii=\"Times New Roman\" w:hAnsi=\"Times New Roman\"\n" +
            "                              w:cs=\"Times New Roman\"/>\n" +
            "                    <w:b/>\n" +
            "                    <w:sz w:val=\"18\"/>\n" +
            "                    <w:szCs w:val=\"18\"/>\n" +
            "                </w:rPr>\n" +
            "                <w:t>输出</w:t>\n" +
            "            </w:r>\n" +
            "        </w:p>\n" +
            "    </w:tc>\n" +
            "</w:tr>\n";
    public static final String sdaRsp = "<w:tr w:rsidR=\"00534A07\" w:rsidRPr=\"00EB31EF\" w:rsidTr=\"00CE3E8F\">\n" +
            "    <w:tc>\n" +
            "        <w:tcPr>\n" +
            "            <w:tcW w:w=\"1217\" w:type=\"dxa\"/>\n" +
            "            <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"00B050\"/>\n" +
            "        </w:tcPr>\n" +
            "        <w:p w:rsidR=\"00534A07\" w:rsidRPr=\"00EB31EF\" w:rsidRDefault=\"00534A07\"\n" +
            "             w:rsidP=\"00D17870\">\n" +
            "            <w:pPr>\n" +
            "                <w:spacing w:line=\"220\" w:lineRule=\"atLeast\"/>\n" +
            "                <w:rPr>\n" +
            "                    <w:rFonts w:ascii=\"Times New Roman\" w:hAnsi=\"Times New Roman\"\n" +
            "                              w:cs=\"Times New Roman\"/>\n" +
            "                    <w:sz w:val=\"18\"/>\n" +
            "                    <w:szCs w:val=\"18\"/>\n" +
            "                </w:rPr>\n" +
            "            </w:pPr>\n" +
            "            <w:r>\n" +
            "                <w:rPr>\n" +
            "                    <w:rFonts w:ascii=\"Times New Roman\" w:hAnsi=\"Times New Roman\"\n" +
            "                              w:cs=\"Times New Roman\"/>\n" +
            "                    <w:sz w:val=\"18\"/>\n" +
            "                    <w:szCs w:val=\"18\"/>\n" +
            "                </w:rPr>\n" +
            "                <w:t>" + rspSeq + "</w:t>\n" +
            "            </w:r>\n" +
            "        </w:p>\n" +
            "    </w:tc>\n" +
            "    <w:tc>\n" +
            "        <w:tcPr>\n" +
            "            <w:tcW w:w=\"1217\" w:type=\"dxa\"/>\n" +
            "            <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"00B050\"/>\n" +
            "        </w:tcPr>\n" +
            "        <w:p w:rsidR=\"00534A07\" w:rsidRPr=\"00EB31EF\" w:rsidRDefault=\"00534A07\"\n" +
            "             w:rsidP=\"00D17870\">\n" +
            "            <w:pPr>\n" +
            "                <w:spacing w:line=\"220\" w:lineRule=\"atLeast\"/>\n" +
            "                <w:rPr>\n" +
            "                    <w:rFonts w:ascii=\"Times New Roman\" w:hAnsi=\"Times New Roman\"\n" +
            "                              w:cs=\"Times New Roman\"/>\n" +
            "                    <w:sz w:val=\"18\"/>\n" +
            "                    <w:szCs w:val=\"18\"/>\n" +
            "                </w:rPr>\n" +
            "            </w:pPr>\n" +
            "            <w:r>\n" +
            "                <w:rPr>\n" +
            "                    <w:rFonts w:ascii=\"Times New Roman\" w:hAnsi=\"Times New Roman\"\n" +
            "                              w:cs=\"Times New Roman\"/>\n" +
            "                    <w:sz w:val=\"18\"/>\n" +
            "                    <w:szCs w:val=\"18\"/>\n" +
            "                </w:rPr>\n" +
            "                <w:t>" + rspStructName + "</w:t>\n" +
            "            </w:r>\n" +
            "        </w:p>\n" +
            "    </w:tc>\n" +
            "    <w:tc>\n" +
            "        <w:tcPr>\n" +
            "            <w:tcW w:w=\"1217\" w:type=\"dxa\"/>\n" +
            "            <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"00B050\"/>\n" +
            "        </w:tcPr>\n" +
            "        <w:p w:rsidR=\"00534A07\" w:rsidRPr=\"00EB31EF\" w:rsidRDefault=\"00534A07\"\n" +
            "             w:rsidP=\"00D17870\">\n" +
            "            <w:pPr>\n" +
            "                <w:spacing w:line=\"220\" w:lineRule=\"atLeast\"/>\n" +
            "                <w:rPr>\n" +
            "                    <w:rFonts w:ascii=\"Times New Roman\" w:hAnsi=\"Times New Roman\"\n" +
            "                              w:cs=\"Times New Roman\"/>\n" +
            "                    <w:sz w:val=\"18\"/>\n" +
            "                    <w:szCs w:val=\"18\"/>\n" +
            "                </w:rPr>\n" +
            "            </w:pPr>\n" +
            "            <w:r>\n" +
            "                <w:rPr>\n" +
            "                    <w:rFonts w:ascii=\"Times New Roman\" w:hAnsi=\"Times New Roman\"\n" +
            "                              w:cs=\"Times New Roman\"/>\n" +
            "                    <w:sz w:val=\"18\"/>\n" +
            "                    <w:szCs w:val=\"18\"/>\n" +
            "                </w:rPr>\n" +
            "                <w:t>" + rspType + "</w:t>\n" +
            "            </w:r>\n" +
            "        </w:p>\n" +
            "    </w:tc>\n" +
            "    <w:tc>\n" +
            "        <w:tcPr>\n" +
            "            <w:tcW w:w=\"1217\" w:type=\"dxa\"/>\n" +
            "            <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"00B050\"/>\n" +
            "        </w:tcPr>\n" +
            "        <w:p w:rsidR=\"00534A07\" w:rsidRPr=\"00EB31EF\" w:rsidRDefault=\"00534A07\"\n" +
            "             w:rsidP=\"00D17870\">\n" +
            "            <w:pPr>\n" +
            "                <w:spacing w:line=\"220\" w:lineRule=\"atLeast\"/>\n" +
            "                <w:rPr>\n" +
            "                    <w:rFonts w:ascii=\"Times New Roman\" w:hAnsi=\"Times New Roman\"\n" +
            "                              w:cs=\"Times New Roman\"/>\n" +
            "                    <w:sz w:val=\"18\"/>\n" +
            "                    <w:szCs w:val=\"18\"/>\n" +
            "                </w:rPr>\n" +
            "            </w:pPr>\n" +
            "            <w:r>\n" +
            "                <w:rPr>\n" +
            "                    <w:rFonts w:ascii=\"Times New Roman\" w:hAnsi=\"Times New Roman\"\n" +
            "                              w:cs=\"Times New Roman\"/>\n" +
            "                    <w:sz w:val=\"18\"/>\n" +
            "                    <w:szCs w:val=\"18\"/>\n" +
            "                </w:rPr>\n" +
            "                <w:t>" + rspStructAlias + "</w:t>\n" +
            "            </w:r>\n" +
            "        </w:p>\n" +
            "    </w:tc>\n" +
            "    <w:tc>\n" +
            "        <w:tcPr>\n" +
            "            <w:tcW w:w=\"1218\" w:type=\"dxa\"/>\n" +
            "            <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"00B050\"/>\n" +
            "        </w:tcPr>\n" +
            "        <w:p w:rsidR=\"00534A07\" w:rsidRPr=\"00EB31EF\" w:rsidRDefault=\"00534A07\"\n" +
            "             w:rsidP=\"00D17870\">\n" +
            "            <w:pPr>\n" +
            "                <w:spacing w:line=\"220\" w:lineRule=\"atLeast\"/>\n" +
            "                <w:rPr>\n" +
            "                    <w:rFonts w:ascii=\"Times New Roman\" w:hAnsi=\"Times New Roman\"\n" +
            "                              w:cs=\"Times New Roman\"/>\n" +
            "                    <w:sz w:val=\"18\"/>\n" +
            "                    <w:szCs w:val=\"18\"/>\n" +
            "                </w:rPr>\n" +
            "            </w:pPr>\n" +
            "            <w:r>\n" +
            "                <w:rPr>\n" +
            "                    <w:rFonts w:ascii=\"Times New Roman\" w:hAnsi=\"Times New Roman\"\n" +
            "                              w:cs=\"Times New Roman\"/>\n" +
            "                    <w:sz w:val=\"18\"/>\n" +
            "                    <w:szCs w:val=\"18\"/>\n" +
            "                </w:rPr>\n" +
            "                <w:t>" + rspRequired + "</w:t>\n" +
            "            </w:r>\n" +
            "        </w:p>\n" +
            "    </w:tc>\n" +
            "    <w:tc>\n" +
            "        <w:tcPr>\n" +
            "            <w:tcW w:w=\"1218\" w:type=\"dxa\"/>\n" +
            "            <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"00B050\"/>\n" +
            "        </w:tcPr>\n" +
            "        <w:p w:rsidR=\"00534A07\" w:rsidRPr=\"00EB31EF\" w:rsidRDefault=\"00534A07\"\n" +
            "             w:rsidP=\"00D17870\">\n" +
            "            <w:pPr>\n" +
            "                <w:spacing w:line=\"220\" w:lineRule=\"atLeast\"/>\n" +
            "                <w:rPr>\n" +
            "                    <w:rFonts w:ascii=\"Times New Roman\" w:hAnsi=\"Times New Roman\"\n" +
            "                              w:cs=\"Times New Roman\"/>\n" +
            "                    <w:sz w:val=\"18\"/>\n" +
            "                    <w:szCs w:val=\"18\"/>\n" +
            "                </w:rPr>\n" +
            "            </w:pPr>\n" +
            "            <w:r>\n" +
            "                <w:rPr>\n" +
            "                    <w:rFonts w:ascii=\"Times New Roman\" w:hAnsi=\"Times New Roman\"\n" +
            "                              w:cs=\"Times New Roman\"/>\n" +
            "                    <w:sz w:val=\"18\"/>\n" +
            "                    <w:szCs w:val=\"18\"/>\n" +
            "                </w:rPr>\n" +
            "                <w:t>" + rspConstraint + "</w:t>\n" +
            "            </w:r>\n" +
            "        </w:p>\n" +
            "    </w:tc>\n" +
            "    <w:tc>\n" +
            "        <w:tcPr>\n" +
            "            <w:tcW w:w=\"1218\" w:type=\"dxa\"/>\n" +
            "            <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"00B050\"/>\n" +
            "        </w:tcPr>\n" +
            "        <w:p w:rsidR=\"00534A07\" w:rsidRPr=\"00EB31EF\" w:rsidRDefault=\"00534A07\"\n" +
            "             w:rsidP=\"00D17870\">\n" +
            "            <w:pPr>\n" +
            "                <w:spacing w:line=\"220\" w:lineRule=\"atLeast\"/>\n" +
            "                <w:rPr>\n" +
            "                    <w:rFonts w:ascii=\"Times New Roman\" w:hAnsi=\"Times New Roman\"\n" +
            "                              w:cs=\"Times New Roman\"/>\n" +
            "                    <w:sz w:val=\"18\"/>\n" +
            "                    <w:szCs w:val=\"18\"/>\n" +
            "                </w:rPr>\n" +
            "            </w:pPr>\n" +
            "            <w:r>\n" +
            "                <w:rPr>\n" +
            "                    <w:rFonts w:ascii=\"Times New Roman\" w:hAnsi=\"Times New Roman\"\n" +
            "                              w:cs=\"Times New Roman\"/>\n" +
            "                    <w:sz w:val=\"18\"/>\n" +
            "                    <w:szCs w:val=\"18\"/>\n" +
            "                </w:rPr>\n" +
            "                <w:t>" + rspRemark + "</w:t>\n" +
            "            </w:r>\n" +
            "        </w:p>\n" +
            "    </w:tc>\n" +
            "</w:tr>\n";
    public static final String operationFoot = "</w:tbl>\n" +
            "<w:p w:rsidR=\"00C749FF\" w:rsidRPr=\"00EB31EF\" w:rsidRDefault=\"00C749FF\" w:rsidP=\"00C749FF\">\n" +
            "    <w:pPr>\n" +
            "        <w:spacing w:line=\"220\" w:lineRule=\"atLeast\"/>\n" +
            "        <w:rPr>\n" +
            "            <w:rFonts w:ascii=\"Times New Roman\" w:hAnsi=\"Times New Roman\" w:cs=\"Times New Roman\"/>\n" +
            "            <w:sz w:val=\"24\"/>\n" +
            "            <w:szCs w:val=\"24\"/>\n" +
            "        </w:rPr>\n" +
            "    </w:pPr>\n" +
            "</w:p>\n" +
            "<w:sectPr w:rsidR=\"00C749FF\" w:rsidRPr=\"00EB31EF\" w:rsidSect=\"004358AB\">\n" +
            "<w:pgSz w:w=\"11906\" w:h=\"16838\"/>\n" +
            "<w:pgMar w:top=\"1440\" w:right=\"1800\" w:bottom=\"1440\" w:left=\"1800\" w:header=\"708\" w:footer=\"708\"\n" +
            "         w:gutter=\"0\"/>\n" +
            "<w:cols w:space=\"708\"/>\n" +
            "<w:docGrid w:linePitch=\"360\"/>\n" +
            "</w:sectPr>\n";
    public static final String foot = "</w:body>\n" +
            "        </w:document>\n" +
            "        </pkg:xmlData>\n" +
            "        </pkg:part>\n" +
            "<pkg:part pkg:name=\"/word/footnotes.xml\"\n" +
            "          pkg:contentType=\"application/vnd.openxmlformats-officedocument.wordprocessingml.footnotes+xml\">\n" +
            "    <pkg:xmlData>\n" +
            "        <w:footnotes xmlns:ve=\"http://schemas.openxmlformats.org/markup-compatibility/2006\"\n" +
            "                     xmlns:o=\"urn:schemas-microsoft-com:office:office\"\n" +
            "                     xmlns:r=\"http://schemas.openxmlformats.org/officeDocument/2006/relationships\"\n" +
            "                     xmlns:m=\"http://schemas.openxmlformats.org/officeDocument/2006/math\"\n" +
            "                     xmlns:v=\"urn:schemas-microsoft-com:vml\"\n" +
            "                     xmlns:wp=\"http://schemas.openxmlformats.org/drawingml/2006/wordprocessingDrawing\"\n" +
            "                     xmlns:w10=\"urn:schemas-microsoft-com:office:word\"\n" +
            "                     xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\"\n" +
            "                     xmlns:wne=\"http://schemas.microsoft.com/office/word/2006/wordml\">\n" +
            "            <w:footnote w:type=\"separator\" w:id=\"-1\">\n" +
            "                <w:p w:rsidR=\"00E02502\" w:rsidRDefault=\"00E02502\" w:rsidP=\"00AD08ED\">\n" +
            "                    <w:pPr>\n" +
            "                        <w:spacing w:after=\"0\"/>\n" +
            "                    </w:pPr>\n" +
            "                    <w:r>\n" +
            "                        <w:separator/>\n" +
            "                    </w:r>\n" +
            "                </w:p>\n" +
            "            </w:footnote>\n" +
            "            <w:footnote w:type=\"continuationSeparator\" w:id=\"0\">\n" +
            "                <w:p w:rsidR=\"00E02502\" w:rsidRDefault=\"00E02502\" w:rsidP=\"00AD08ED\">\n" +
            "                    <w:pPr>\n" +
            "                        <w:spacing w:after=\"0\"/>\n" +
            "                    </w:pPr>\n" +
            "                    <w:r>\n" +
            "                        <w:continuationSeparator/>\n" +
            "                    </w:r>\n" +
            "                </w:p>\n" +
            "            </w:footnote>\n" +
            "        </w:footnotes>\n" +
            "    </pkg:xmlData>\n" +
            "</pkg:part>\n" +
            "<pkg:part pkg:name=\"/word/endnotes.xml\"\n" +
            "          pkg:contentType=\"application/vnd.openxmlformats-officedocument.wordprocessingml.endnotes+xml\">\n" +
            "<pkg:xmlData>\n" +
            "    <w:endnotes xmlns:ve=\"http://schemas.openxmlformats.org/markup-compatibility/2006\"\n" +
            "                xmlns:o=\"urn:schemas-microsoft-com:office:office\"\n" +
            "                xmlns:r=\"http://schemas.openxmlformats.org/officeDocument/2006/relationships\"\n" +
            "                xmlns:m=\"http://schemas.openxmlformats.org/officeDocument/2006/math\"\n" +
            "                xmlns:v=\"urn:schemas-microsoft-com:vml\"\n" +
            "                xmlns:wp=\"http://schemas.openxmlformats.org/drawingml/2006/wordprocessingDrawing\"\n" +
            "                xmlns:w10=\"urn:schemas-microsoft-com:office:word\"\n" +
            "                xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\"\n" +
            "                xmlns:wne=\"http://schemas.microsoft.com/office/word/2006/wordml\">\n" +
            "        <w:endnote w:type=\"separator\" w:id=\"-1\">\n" +
            "            <w:p w:rsidR=\"00E02502\" w:rsidRDefault=\"00E02502\" w:rsidP=\"00AD08ED\">\n" +
            "                <w:pPr>\n" +
            "                    <w:spacing w:after=\"0\"/>\n" +
            "                </w:pPr>\n" +
            "                <w:r>\n" +
            "                    <w:separator/>\n" +
            "                </w:r>\n" +
            "            </w:p>\n" +
            "        </w:endnote>\n" +
            "        <w:endnote w:type=\"continuationSeparator\" w:id=\"0\">\n" +
            "            <w:p w:rsidR=\"00E02502\" w:rsidRDefault=\"00E02502\" w:rsidP=\"00AD08ED\">\n" +
            "                <w:pPr>\n" +
            "                    <w:spacing w:after=\"0\"/>\n" +
            "                </w:pPr>\n" +
            "                <w:r>\n" +
            "                    <w:continuationSeparator/>\n" +
            "                </w:r>\n" +
            "            </w:p>\n" +
            "        </w:endnote>\n" +
            "    </w:endnotes>\n" +
            "</pkg:xmlData>\n" +
            "</pkg:part>\n" +
            "<pkg:part pkg:name=\"/word/theme/theme1.xml\"\n" +
            "          pkg:contentType=\"application/vnd.openxmlformats-officedocument.theme+xml\">\n" +
            "<pkg:xmlData>\n" +
            "    <a:theme name=\"Office 主题\" xmlns:a=\"http://schemas.openxmlformats.org/drawingml/2006/main\">\n" +
            "        <a:themeElements>\n" +
            "            <a:clrScheme name=\"Office\">\n" +
            "                <a:dk1>\n" +
            "                    <a:sysClr val=\"windowText\" lastClr=\"000000\"/>\n" +
            "                </a:dk1>\n" +
            "                <a:lt1>\n" +
            "                    <a:sysClr val=\"window\" lastClr=\"FFFFFF\"/>\n" +
            "                </a:lt1>\n" +
            "                <a:dk2>\n" +
            "                    <a:srgbClr val=\"1F497D\"/>\n" +
            "                </a:dk2>\n" +
            "                <a:lt2>\n" +
            "                    <a:srgbClr val=\"EEECE1\"/>\n" +
            "                </a:lt2>\n" +
            "                <a:accent1>\n" +
            "                    <a:srgbClr val=\"4F81BD\"/>\n" +
            "                </a:accent1>\n" +
            "                <a:accent2>\n" +
            "                    <a:srgbClr val=\"C0504D\"/>\n" +
            "                </a:accent2>\n" +
            "                <a:accent3>\n" +
            "                    <a:srgbClr val=\"9BBB59\"/>\n" +
            "                </a:accent3>\n" +
            "                <a:accent4>\n" +
            "                    <a:srgbClr val=\"8064A2\"/>\n" +
            "                </a:accent4>\n" +
            "                <a:accent5>\n" +
            "                    <a:srgbClr val=\"4BACC6\"/>\n" +
            "                </a:accent5>\n" +
            "                <a:accent6>\n" +
            "                    <a:srgbClr val=\"F79646\"/>\n" +
            "                </a:accent6>\n" +
            "                <a:hlink>\n" +
            "                    <a:srgbClr val=\"0000FF\"/>\n" +
            "                </a:hlink>\n" +
            "                <a:folHlink>\n" +
            "                    <a:srgbClr val=\"800080\"/>\n" +
            "                </a:folHlink>\n" +
            "            </a:clrScheme>\n" +
            "            <a:fontScheme name=\"Office\">\n" +
            "                <a:majorFont>\n" +
            "                    <a:latin typeface=\"Cambria\"/>\n" +
            "                    <a:ea typeface=\"\"/>\n" +
            "                    <a:cs typeface=\"\"/>\n" +
            "                    <a:font script=\"Jpan\" typeface=\"ＭＳ ゴシック\"/>\n" +
            "                    <a:font script=\"Hang\" typeface=\"맑은 고딕\"/>\n" +
            "                    <a:font script=\"Hans\" typeface=\"宋体\"/>\n" +
            "                    <a:font script=\"Hant\" typeface=\"新細明體\"/>\n" +
            "                    <a:font script=\"Arab\" typeface=\"Times New Roman\"/>\n" +
            "                    <a:font script=\"Hebr\" typeface=\"Times New Roman\"/>\n" +
            "                    <a:font script=\"Thai\" typeface=\"Angsana New\"/>\n" +
            "                    <a:font script=\"Ethi\" typeface=\"Nyala\"/>\n" +
            "                    <a:font script=\"Beng\" typeface=\"Vrinda\"/>\n" +
            "                    <a:font script=\"Gujr\" typeface=\"Shruti\"/>\n" +
            "                    <a:font script=\"Khmr\" typeface=\"MoolBoran\"/>\n" +
            "                    <a:font script=\"Knda\" typeface=\"Tunga\"/>\n" +
            "                    <a:font script=\"Guru\" typeface=\"Raavi\"/>\n" +
            "                    <a:font script=\"Cans\" typeface=\"Euphemia\"/>\n" +
            "                    <a:font script=\"Cher\" typeface=\"Plantagenet Cherokee\"/>\n" +
            "                    <a:font script=\"Yiii\" typeface=\"Microsoft Yi Baiti\"/>\n" +
            "                    <a:font script=\"Tibt\" typeface=\"Microsoft Himalaya\"/>\n" +
            "                    <a:font script=\"Thaa\" typeface=\"MV Boli\"/>\n" +
            "                    <a:font script=\"Deva\" typeface=\"Mangal\"/>\n" +
            "                    <a:font script=\"Telu\" typeface=\"Gautami\"/>\n" +
            "                    <a:font script=\"Taml\" typeface=\"Latha\"/>\n" +
            "                    <a:font script=\"Syrc\" typeface=\"Estrangelo Edessa\"/>\n" +
            "                    <a:font script=\"Orya\" typeface=\"Kalinga\"/>\n" +
            "                    <a:font script=\"Mlym\" typeface=\"Kartika\"/>\n" +
            "                    <a:font script=\"Laoo\" typeface=\"DokChampa\"/>\n" +
            "                    <a:font script=\"Sinh\" typeface=\"Iskoola Pota\"/>\n" +
            "                    <a:font script=\"Mong\" typeface=\"Mongolian Baiti\"/>\n" +
            "                    <a:font script=\"Viet\" typeface=\"Times New Roman\"/>\n" +
            "                    <a:font script=\"Uigh\" typeface=\"Microsoft Uighur\"/>\n" +
            "                </a:majorFont>\n" +
            "                <a:minorFont>\n" +
            "                    <a:latin typeface=\"Calibri\"/>\n" +
            "                    <a:ea typeface=\"\"/>\n" +
            "                    <a:cs typeface=\"\"/>\n" +
            "                    <a:font script=\"Jpan\" typeface=\"ＭＳ 明朝\"/>\n" +
            "                    <a:font script=\"Hang\" typeface=\"맑은 고딕\"/>\n" +
            "                    <a:font script=\"Hans\" typeface=\"宋体\"/>\n" +
            "                    <a:font script=\"Hant\" typeface=\"新細明體\"/>\n" +
            "                    <a:font script=\"Arab\" typeface=\"Arial\"/>\n" +
            "                    <a:font script=\"Hebr\" typeface=\"Arial\"/>\n" +
            "                    <a:font script=\"Thai\" typeface=\"Cordia New\"/>\n" +
            "                    <a:font script=\"Ethi\" typeface=\"Nyala\"/>\n" +
            "                    <a:font script=\"Beng\" typeface=\"Vrinda\"/>\n" +
            "                    <a:font script=\"Gujr\" typeface=\"Shruti\"/>\n" +
            "                    <a:font script=\"Khmr\" typeface=\"DaunPenh\"/>\n" +
            "                    <a:font script=\"Knda\" typeface=\"Tunga\"/>\n" +
            "                    <a:font script=\"Guru\" typeface=\"Raavi\"/>\n" +
            "                    <a:font script=\"Cans\" typeface=\"Euphemia\"/>\n" +
            "                    <a:font script=\"Cher\" typeface=\"Plantagenet Cherokee\"/>\n" +
            "                    <a:font script=\"Yiii\" typeface=\"Microsoft Yi Baiti\"/>\n" +
            "                    <a:font script=\"Tibt\" typeface=\"Microsoft Himalaya\"/>\n" +
            "                    <a:font script=\"Thaa\" typeface=\"MV Boli\"/>\n" +
            "                    <a:font script=\"Deva\" typeface=\"Mangal\"/>\n" +
            "                    <a:font script=\"Telu\" typeface=\"Gautami\"/>\n" +
            "                    <a:font script=\"Taml\" typeface=\"Latha\"/>\n" +
            "                    <a:font script=\"Syrc\" typeface=\"Estrangelo Edessa\"/>\n" +
            "                    <a:font script=\"Orya\" typeface=\"Kalinga\"/>\n" +
            "                    <a:font script=\"Mlym\" typeface=\"Kartika\"/>\n" +
            "                    <a:font script=\"Laoo\" typeface=\"DokChampa\"/>\n" +
            "                    <a:font script=\"Sinh\" typeface=\"Iskoola Pota\"/>\n" +
            "                    <a:font script=\"Mong\" typeface=\"Mongolian Baiti\"/>\n" +
            "                    <a:font script=\"Viet\" typeface=\"Arial\"/>\n" +
            "                    <a:font script=\"Uigh\" typeface=\"Microsoft Uighur\"/>\n" +
            "                </a:minorFont>\n" +
            "            </a:fontScheme>\n" +
            "            <a:fmtScheme name=\"Office\">\n" +
            "                <a:fillStyleLst>\n" +
            "                    <a:solidFill>\n" +
            "                        <a:schemeClr val=\"phClr\"/>\n" +
            "                    </a:solidFill>\n" +
            "                    <a:gradFill rotWithShape=\"1\">\n" +
            "                        <a:gsLst>\n" +
            "                            <a:gs pos=\"0\">\n" +
            "                                <a:schemeClr val=\"phClr\">\n" +
            "                                    <a:tint val=\"50000\"/>\n" +
            "                                    <a:satMod val=\"300000\"/>\n" +
            "                                </a:schemeClr>\n" +
            "                            </a:gs>\n" +
            "                            <a:gs pos=\"35000\">\n" +
            "                                <a:schemeClr val=\"phClr\">\n" +
            "                                    <a:tint val=\"37000\"/>\n" +
            "                                    <a:satMod val=\"300000\"/>\n" +
            "                                </a:schemeClr>\n" +
            "                            </a:gs>\n" +
            "                            <a:gs pos=\"100000\">\n" +
            "                                <a:schemeClr val=\"phClr\">\n" +
            "                                    <a:tint val=\"15000\"/>\n" +
            "                                    <a:satMod val=\"350000\"/>\n" +
            "                                </a:schemeClr>\n" +
            "                            </a:gs>\n" +
            "                        </a:gsLst>\n" +
            "                        <a:lin ang=\"16200000\" scaled=\"1\"/>\n" +
            "                    </a:gradFill>\n" +
            "                    <a:gradFill rotWithShape=\"1\">\n" +
            "                        <a:gsLst>\n" +
            "                            <a:gs pos=\"0\">\n" +
            "                                <a:schemeClr val=\"phClr\">\n" +
            "                                    <a:shade val=\"51000\"/>\n" +
            "                                    <a:satMod val=\"130000\"/>\n" +
            "                                </a:schemeClr>\n" +
            "                            </a:gs>\n" +
            "                            <a:gs pos=\"80000\">\n" +
            "                                <a:schemeClr val=\"phClr\">\n" +
            "                                    <a:shade val=\"93000\"/>\n" +
            "                                    <a:satMod val=\"130000\"/>\n" +
            "                                </a:schemeClr>\n" +
            "                            </a:gs>\n" +
            "                            <a:gs pos=\"100000\">\n" +
            "                                <a:schemeClr val=\"phClr\">\n" +
            "                                    <a:shade val=\"94000\"/>\n" +
            "                                    <a:satMod val=\"135000\"/>\n" +
            "                                </a:schemeClr>\n" +
            "                            </a:gs>\n" +
            "                        </a:gsLst>\n" +
            "                        <a:lin ang=\"16200000\" scaled=\"0\"/>\n" +
            "                    </a:gradFill>\n" +
            "                </a:fillStyleLst>\n" +
            "                <a:lnStyleLst>\n" +
            "                    <a:ln w=\"9525\" cap=\"flat\" cmpd=\"sng\" algn=\"ctr\">\n" +
            "                        <a:solidFill>\n" +
            "                            <a:schemeClr val=\"phClr\">\n" +
            "                                <a:shade val=\"95000\"/>\n" +
            "                                <a:satMod val=\"105000\"/>\n" +
            "                            </a:schemeClr>\n" +
            "                        </a:solidFill>\n" +
            "                        <a:prstDash val=\"solid\"/>\n" +
            "                    </a:ln>\n" +
            "                    <a:ln w=\"25400\" cap=\"flat\" cmpd=\"sng\" algn=\"ctr\">\n" +
            "                        <a:solidFill>\n" +
            "                            <a:schemeClr val=\"phClr\"/>\n" +
            "                        </a:solidFill>\n" +
            "                        <a:prstDash val=\"solid\"/>\n" +
            "                    </a:ln>\n" +
            "                    <a:ln w=\"38100\" cap=\"flat\" cmpd=\"sng\" algn=\"ctr\">\n" +
            "                        <a:solidFill>\n" +
            "                            <a:schemeClr val=\"phClr\"/>\n" +
            "                        </a:solidFill>\n" +
            "                        <a:prstDash val=\"solid\"/>\n" +
            "                    </a:ln>\n" +
            "                </a:lnStyleLst>\n" +
            "                <a:effectStyleLst>\n" +
            "                    <a:effectStyle>\n" +
            "                        <a:effectLst>\n" +
            "                            <a:outerShdw blurRad=\"40000\" dist=\"20000\" dir=\"5400000\" rotWithShape=\"0\">\n" +
            "                                <a:srgbClr val=\"000000\">\n" +
            "                                    <a:alpha val=\"38000\"/>\n" +
            "                                </a:srgbClr>\n" +
            "                            </a:outerShdw>\n" +
            "                        </a:effectLst>\n" +
            "                    </a:effectStyle>\n" +
            "                    <a:effectStyle>\n" +
            "                        <a:effectLst>\n" +
            "                            <a:outerShdw blurRad=\"40000\" dist=\"23000\" dir=\"5400000\" rotWithShape=\"0\">\n" +
            "                                <a:srgbClr val=\"000000\">\n" +
            "                                    <a:alpha val=\"35000\"/>\n" +
            "                                </a:srgbClr>\n" +
            "                            </a:outerShdw>\n" +
            "                        </a:effectLst>\n" +
            "                    </a:effectStyle>\n" +
            "                    <a:effectStyle>\n" +
            "                        <a:effectLst>\n" +
            "                            <a:outerShdw blurRad=\"40000\" dist=\"23000\" dir=\"5400000\" rotWithShape=\"0\">\n" +
            "                                <a:srgbClr val=\"000000\">\n" +
            "                                    <a:alpha val=\"35000\"/>\n" +
            "                                </a:srgbClr>\n" +
            "                            </a:outerShdw>\n" +
            "                        </a:effectLst>\n" +
            "                        <a:scene3d>\n" +
            "                            <a:camera prst=\"orthographicFront\">\n" +
            "                                <a:rot lat=\"0\" lon=\"0\" rev=\"0\"/>\n" +
            "                            </a:camera>\n" +
            "                            <a:lightRig rig=\"threePt\" dir=\"t\">\n" +
            "                                <a:rot lat=\"0\" lon=\"0\" rev=\"1200000\"/>\n" +
            "                            </a:lightRig>\n" +
            "                        </a:scene3d>\n" +
            "                        <a:sp3d>\n" +
            "                            <a:bevelT w=\"63500\" h=\"25400\"/>\n" +
            "                        </a:sp3d>\n" +
            "                    </a:effectStyle>\n" +
            "                </a:effectStyleLst>\n" +
            "                <a:bgFillStyleLst>\n" +
            "                    <a:solidFill>\n" +
            "                        <a:schemeClr val=\"phClr\"/>\n" +
            "                    </a:solidFill>\n" +
            "                    <a:gradFill rotWithShape=\"1\">\n" +
            "                        <a:gsLst>\n" +
            "                            <a:gs pos=\"0\">\n" +
            "                                <a:schemeClr val=\"phClr\">\n" +
            "                                    <a:tint val=\"40000\"/>\n" +
            "                                    <a:satMod val=\"350000\"/>\n" +
            "                                </a:schemeClr>\n" +
            "                            </a:gs>\n" +
            "                            <a:gs pos=\"40000\">\n" +
            "                                <a:schemeClr val=\"phClr\">\n" +
            "                                    <a:tint val=\"45000\"/>\n" +
            "                                    <a:shade val=\"99000\"/>\n" +
            "                                    <a:satMod val=\"350000\"/>\n" +
            "                                </a:schemeClr>\n" +
            "                            </a:gs>\n" +
            "                            <a:gs pos=\"100000\">\n" +
            "                                <a:schemeClr val=\"phClr\">\n" +
            "                                    <a:shade val=\"20000\"/>\n" +
            "                                    <a:satMod val=\"255000\"/>\n" +
            "                                </a:schemeClr>\n" +
            "                            </a:gs>\n" +
            "                        </a:gsLst>\n" +
            "                        <a:path path=\"circle\">\n" +
            "                            <a:fillToRect l=\"50000\" t=\"-80000\" r=\"50000\" b=\"180000\"/>\n" +
            "                        </a:path>\n" +
            "                    </a:gradFill>\n" +
            "                    <a:gradFill rotWithShape=\"1\">\n" +
            "                        <a:gsLst>\n" +
            "                            <a:gs pos=\"0\">\n" +
            "                                <a:schemeClr val=\"phClr\">\n" +
            "                                    <a:tint val=\"80000\"/>\n" +
            "                                    <a:satMod val=\"300000\"/>\n" +
            "                                </a:schemeClr>\n" +
            "                            </a:gs>\n" +
            "                            <a:gs pos=\"100000\">\n" +
            "                                <a:schemeClr val=\"phClr\">\n" +
            "                                    <a:shade val=\"30000\"/>\n" +
            "                                    <a:satMod val=\"200000\"/>\n" +
            "                                </a:schemeClr>\n" +
            "                            </a:gs>\n" +
            "                        </a:gsLst>\n" +
            "                        <a:path path=\"circle\">\n" +
            "                            <a:fillToRect l=\"50000\" t=\"50000\" r=\"50000\" b=\"50000\"/>\n" +
            "                        </a:path>\n" +
            "                    </a:gradFill>\n" +
            "                </a:bgFillStyleLst>\n" +
            "            </a:fmtScheme>\n" +
            "        </a:themeElements>\n" +
            "        <a:objectDefaults/>\n" +
            "        <a:extraClrSchemeLst/>\n" +
            "    </a:theme>\n" +
            "</pkg:xmlData>\n" +
            "</pkg:part>\n" +
            "<pkg:part pkg:name=\"/word/settings.xml\"\n" +
            "          pkg:contentType=\"application/vnd.openxmlformats-officedocument.wordprocessingml.settings+xml\">\n" +
            "<pkg:xmlData>\n" +
            "    <w:settings xmlns:o=\"urn:schemas-microsoft-com:office:office\"\n" +
            "                xmlns:r=\"http://schemas.openxmlformats.org/officeDocument/2006/relationships\"\n" +
            "                xmlns:m=\"http://schemas.openxmlformats.org/officeDocument/2006/math\"\n" +
            "                xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:w10=\"urn:schemas-microsoft-com:office:word\"\n" +
            "                xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\"\n" +
            "                xmlns:sl=\"http://schemas.openxmlformats.org/schemaLibrary/2006/main\">\n" +
            "        <w:zoom w:percent=\"100\"/>\n" +
            "        <w:bordersDoNotSurroundHeader/>\n" +
            "        <w:bordersDoNotSurroundFooter/>\n" +
            "        <w:defaultTabStop w:val=\"720\"/>\n" +
            "        <w:characterSpacingControl w:val=\"doNotCompress\"/>\n" +
            "        <w:hdrShapeDefaults>\n" +
            "            <o:shapedefaults v:ext=\"edit\" spidmax=\"8194\"/>\n" +
            "        </w:hdrShapeDefaults>\n" +
            "        <w:footnotePr>\n" +
            "            <w:footnote w:id=\"-1\"/>\n" +
            "            <w:footnote w:id=\"0\"/>\n" +
            "        </w:footnotePr>\n" +
            "        <w:endnotePr>\n" +
            "            <w:endnote w:id=\"-1\"/>\n" +
            "            <w:endnote w:id=\"0\"/>\n" +
            "        </w:endnotePr>\n" +
            "        <w:compat>\n" +
            "            <w:useFELayout/>\n" +
            "        </w:compat>\n" +
            "        <w:rsids>\n" +
            "            <w:rsidRoot w:val=\"00D31D50\"/>\n" +
            "            <w:rsid w:val=\"00323B43\"/>\n" +
            "            <w:rsid w:val=\"0032763F\"/>\n" +
            "            <w:rsid w:val=\"003D37D8\"/>\n" +
            "            <w:rsid w:val=\"00426133\"/>\n" +
            "            <w:rsid w:val=\"004358AB\"/>\n" +
            "            <w:rsid w:val=\"00534A07\"/>\n" +
            "            <w:rsid w:val=\"00601CA3\"/>\n" +
            "            <w:rsid w:val=\"008B7726\"/>\n" +
            "            <w:rsid w:val=\"00954670\"/>\n" +
            "            <w:rsid w:val=\"00AD08ED\"/>\n" +
            "            <w:rsid w:val=\"00C749FF\"/>\n" +
            "            <w:rsid w:val=\"00CE3E8F\"/>\n" +
            "            <w:rsid w:val=\"00D31D50\"/>\n" +
            "            <w:rsid w:val=\"00E02502\"/>\n" +
            "            <w:rsid w:val=\"00EB31EF\"/>\n" +
            "            <w:rsid w:val=\"00F03A5A\"/>\n" +
            "        </w:rsids>\n" +
            "        <m:mathPr>\n" +
            "            <m:mathFont m:val=\"Cambria Math\"/>\n" +
            "            <m:brkBin m:val=\"before\"/>\n" +
            "            <m:brkBinSub m:val=\"--\"/>\n" +
            "            <m:smallFrac m:val=\"off\"/>\n" +
            "            <m:dispDef/>\n" +
            "            <m:lMargin m:val=\"0\"/>\n" +
            "            <m:rMargin m:val=\"0\"/>\n" +
            "            <m:defJc m:val=\"centerGroup\"/>\n" +
            "            <m:wrapIndent m:val=\"1440\"/>\n" +
            "            <m:intLim m:val=\"subSup\"/>\n" +
            "            <m:naryLim m:val=\"undOvr\"/>\n" +
            "        </m:mathPr>\n" +
            "        <w:themeFontLang w:val=\"en-US\" w:eastAsia=\"zh-CN\"/>\n" +
            "        <w:clrSchemeMapping w:bg1=\"light1\" w:t1=\"dark1\" w:bg2=\"light2\" w:t2=\"dark2\" w:accent1=\"accent1\"\n" +
            "                            w:accent2=\"accent2\" w:accent3=\"accent3\" w:accent4=\"accent4\" w:accent5=\"accent5\"\n" +
            "                            w:accent6=\"accent6\" w:hyperlink=\"hyperlink\"\n" +
            "                            w:followedHyperlink=\"followedHyperlink\"/>\n" +
            "        <w:shapeDefaults>\n" +
            "            <o:shapedefaults v:ext=\"edit\" spidmax=\"8194\"/>\n" +
            "            <o:shapelayout v:ext=\"edit\">\n" +
            "                <o:idmap v:ext=\"edit\" data=\"1\"/>\n" +
            "            </o:shapelayout>\n" +
            "        </w:shapeDefaults>\n" +
            "        <w:decimalSymbol w:val=\".\"/>\n" +
            "        <w:listSeparator w:val=\",\"/>\n" +
            "    </w:settings>\n" +
            "</pkg:xmlData>\n" +
            "</pkg:part>\n" +
            "<pkg:part pkg:name=\"/word/webSettings.xml\"\n" +
            "          pkg:contentType=\"application/vnd.openxmlformats-officedocument.wordprocessingml.webSettings+xml\">\n" +
            "<pkg:xmlData>\n" +
            "    <w:webSettings xmlns:r=\"http://schemas.openxmlformats.org/officeDocument/2006/relationships\"\n" +
            "                   xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\">\n" +
            "        <w:optimizeForBrowser/>\n" +
            "    </w:webSettings>\n" +
            "</pkg:xmlData>\n" +
            "</pkg:part>\n" +
            "<pkg:part pkg:name=\"/docProps/core.xml\" pkg:contentType=\"application/vnd.openxmlformats-package.core-properties+xml\"\n" +
            "          pkg:padding=\"256\">\n" +
            "<pkg:xmlData>\n" +
            "    <cp:coreProperties xmlns:cp=\"http://schemas.openxmlformats.org/package/2006/metadata/core-properties\"\n" +
            "                       xmlns:dc=\"http://purl.org/dc/elements/1.1/\" xmlns:dcterms=\"http://purl.org/dc/terms/\"\n" +
            "                       xmlns:dcmitype=\"http://purl.org/dc/dcmitype/\"\n" +
            "                       xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n" +
            "        <dc:creator>Administrator</dc:creator>\n" +
            "        <cp:lastModifiedBy>Administrator</cp:lastModifiedBy>\n" +
            "        <cp:revision>5</cp:revision>\n" +
            "        <dcterms:created xsi:type=\"dcterms:W3CDTF\">2016-02-02T08:54:00Z</dcterms:created>\n" +
            "        <dcterms:modified xsi:type=\"dcterms:W3CDTF\">2016-02-02T09:01:00Z</dcterms:modified>\n" +
            "    </cp:coreProperties>\n" +
            "</pkg:xmlData>\n" +
            "</pkg:part>\n" +
            "<pkg:part pkg:name=\"/word/styles.xml\"\n" +
            "          pkg:contentType=\"application/vnd.openxmlformats-officedocument.wordprocessingml.styles+xml\">\n" +
            "<pkg:xmlData>\n" +
            "    <w:styles xmlns:r=\"http://schemas.openxmlformats.org/officeDocument/2006/relationships\"\n" +
            "              xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\">\n" +
            "        <w:docDefaults>\n" +
            "            <w:rPrDefault>\n" +
            "                <w:rPr>\n" +
            "                    <w:rFonts w:asciiTheme=\"minorHAnsi\" w:eastAsia=\"微软雅黑\" w:hAnsiTheme=\"minorHAnsi\"\n" +
            "                              w:cstheme=\"minorBidi\"/>\n" +
            "                    <w:sz w:val=\"22\"/>\n" +
            "                    <w:szCs w:val=\"22\"/>\n" +
            "                    <w:lang w:val=\"en-US\" w:eastAsia=\"zh-CN\" w:bidi=\"ar-SA\"/>\n" +
            "                </w:rPr>\n" +
            "            </w:rPrDefault>\n" +
            "            <w:pPrDefault>\n" +
            "                <w:pPr>\n" +
            "                    <w:spacing w:after=\"200\" w:line=\"220\" w:lineRule=\"atLeast\"/>\n" +
            "                </w:pPr>\n" +
            "            </w:pPrDefault>\n" +
            "        </w:docDefaults>\n" +
            "        <w:latentStyles w:defLockedState=\"0\" w:defUIPriority=\"99\" w:defSemiHidden=\"1\" w:defUnhideWhenUsed=\"1\"\n" +
            "                        w:defQFormat=\"0\" w:count=\"267\">\n" +
            "            <w:lsdException w:name=\"Normal\" w:semiHidden=\"0\" w:uiPriority=\"0\" w:unhideWhenUsed=\"0\"\n" +
            "                            w:qFormat=\"1\"/>\n" +
            "            <w:lsdException w:name=\"heading 1\" w:semiHidden=\"0\" w:uiPriority=\"9\" w:unhideWhenUsed=\"0\"\n" +
            "                            w:qFormat=\"1\"/>\n" +
            "            <w:lsdException w:name=\"heading 2\" w:uiPriority=\"9\" w:qFormat=\"1\"/>\n" +
            "            <w:lsdException w:name=\"heading 3\" w:uiPriority=\"9\" w:qFormat=\"1\"/>\n" +
            "            <w:lsdException w:name=\"heading 4\" w:uiPriority=\"9\" w:qFormat=\"1\"/>\n" +
            "            <w:lsdException w:name=\"heading 5\" w:uiPriority=\"9\" w:qFormat=\"1\"/>\n" +
            "            <w:lsdException w:name=\"heading 6\" w:uiPriority=\"9\" w:qFormat=\"1\"/>\n" +
            "            <w:lsdException w:name=\"heading 7\" w:uiPriority=\"9\" w:qFormat=\"1\"/>\n" +
            "            <w:lsdException w:name=\"heading 8\" w:uiPriority=\"9\" w:qFormat=\"1\"/>\n" +
            "            <w:lsdException w:name=\"heading 9\" w:uiPriority=\"9\" w:qFormat=\"1\"/>\n" +
            "            <w:lsdException w:name=\"toc 1\" w:uiPriority=\"39\"/>\n" +
            "            <w:lsdException w:name=\"toc 2\" w:uiPriority=\"39\"/>\n" +
            "            <w:lsdException w:name=\"toc 3\" w:uiPriority=\"39\"/>\n" +
            "            <w:lsdException w:name=\"toc 4\" w:uiPriority=\"39\"/>\n" +
            "            <w:lsdException w:name=\"toc 5\" w:uiPriority=\"39\"/>\n" +
            "            <w:lsdException w:name=\"toc 6\" w:uiPriority=\"39\"/>\n" +
            "            <w:lsdException w:name=\"toc 7\" w:uiPriority=\"39\"/>\n" +
            "            <w:lsdException w:name=\"toc 8\" w:uiPriority=\"39\"/>\n" +
            "            <w:lsdException w:name=\"toc 9\" w:uiPriority=\"39\"/>\n" +
            "            <w:lsdException w:name=\"caption\" w:uiPriority=\"35\" w:qFormat=\"1\"/>\n" +
            "            <w:lsdException w:name=\"Title\" w:semiHidden=\"0\" w:uiPriority=\"10\" w:unhideWhenUsed=\"0\"\n" +
            "                            w:qFormat=\"1\"/>\n" +
            "            <w:lsdException w:name=\"Default Paragraph Font\" w:uiPriority=\"1\"/>\n" +
            "            <w:lsdException w:name=\"Subtitle\" w:semiHidden=\"0\" w:uiPriority=\"11\" w:unhideWhenUsed=\"0\"\n" +
            "                            w:qFormat=\"1\"/>\n" +
            "            <w:lsdException w:name=\"Strong\" w:semiHidden=\"0\" w:uiPriority=\"22\" w:unhideWhenUsed=\"0\"\n" +
            "                            w:qFormat=\"1\"/>\n" +
            "            <w:lsdException w:name=\"Emphasis\" w:semiHidden=\"0\" w:uiPriority=\"20\" w:unhideWhenUsed=\"0\"\n" +
            "                            w:qFormat=\"1\"/>\n" +
            "            <w:lsdException w:name=\"Table Grid\" w:semiHidden=\"0\" w:uiPriority=\"59\" w:unhideWhenUsed=\"0\"/>\n" +
            "            <w:lsdException w:name=\"Placeholder Text\" w:unhideWhenUsed=\"0\"/>\n" +
            "            <w:lsdException w:name=\"No Spacing\" w:semiHidden=\"0\" w:uiPriority=\"1\" w:unhideWhenUsed=\"0\"\n" +
            "                            w:qFormat=\"1\"/>\n" +
            "            <w:lsdException w:name=\"Light Shading\" w:semiHidden=\"0\" w:uiPriority=\"60\" w:unhideWhenUsed=\"0\"/>\n" +
            "            <w:lsdException w:name=\"Light List\" w:semiHidden=\"0\" w:uiPriority=\"61\" w:unhideWhenUsed=\"0\"/>\n" +
            "            <w:lsdException w:name=\"Light Grid\" w:semiHidden=\"0\" w:uiPriority=\"62\" w:unhideWhenUsed=\"0\"/>\n" +
            "            <w:lsdException w:name=\"Medium Shading 1\" w:semiHidden=\"0\" w:uiPriority=\"63\" w:unhideWhenUsed=\"0\"/>\n" +
            "            <w:lsdException w:name=\"Medium Shading 2\" w:semiHidden=\"0\" w:uiPriority=\"64\" w:unhideWhenUsed=\"0\"/>\n" +
            "            <w:lsdException w:name=\"Medium List 1\" w:semiHidden=\"0\" w:uiPriority=\"65\" w:unhideWhenUsed=\"0\"/>\n" +
            "            <w:lsdException w:name=\"Medium List 2\" w:semiHidden=\"0\" w:uiPriority=\"66\" w:unhideWhenUsed=\"0\"/>\n" +
            "            <w:lsdException w:name=\"Medium Grid 1\" w:semiHidden=\"0\" w:uiPriority=\"67\" w:unhideWhenUsed=\"0\"/>\n" +
            "            <w:lsdException w:name=\"Medium Grid 2\" w:semiHidden=\"0\" w:uiPriority=\"68\" w:unhideWhenUsed=\"0\"/>\n" +
            "            <w:lsdException w:name=\"Medium Grid 3\" w:semiHidden=\"0\" w:uiPriority=\"69\" w:unhideWhenUsed=\"0\"/>\n" +
            "            <w:lsdException w:name=\"Dark List\" w:semiHidden=\"0\" w:uiPriority=\"70\" w:unhideWhenUsed=\"0\"/>\n" +
            "            <w:lsdException w:name=\"Colorful Shading\" w:semiHidden=\"0\" w:uiPriority=\"71\" w:unhideWhenUsed=\"0\"/>\n" +
            "            <w:lsdException w:name=\"Colorful List\" w:semiHidden=\"0\" w:uiPriority=\"72\" w:unhideWhenUsed=\"0\"/>\n" +
            "            <w:lsdException w:name=\"Colorful Grid\" w:semiHidden=\"0\" w:uiPriority=\"73\" w:unhideWhenUsed=\"0\"/>\n" +
            "            <w:lsdException w:name=\"Light Shading Accent 1\" w:semiHidden=\"0\" w:uiPriority=\"60\"\n" +
            "                            w:unhideWhenUsed=\"0\"/>\n" +
            "            <w:lsdException w:name=\"Light List Accent 1\" w:semiHidden=\"0\" w:uiPriority=\"61\"\n" +
            "                            w:unhideWhenUsed=\"0\"/>\n" +
            "            <w:lsdException w:name=\"Light Grid Accent 1\" w:semiHidden=\"0\" w:uiPriority=\"62\"\n" +
            "                            w:unhideWhenUsed=\"0\"/>\n" +
            "            <w:lsdException w:name=\"Medium Shading 1 Accent 1\" w:semiHidden=\"0\" w:uiPriority=\"63\"\n" +
            "                            w:unhideWhenUsed=\"0\"/>\n" +
            "            <w:lsdException w:name=\"Medium Shading 2 Accent 1\" w:semiHidden=\"0\" w:uiPriority=\"64\"\n" +
            "                            w:unhideWhenUsed=\"0\"/>\n" +
            "            <w:lsdException w:name=\"Medium List 1 Accent 1\" w:semiHidden=\"0\" w:uiPriority=\"65\"\n" +
            "                            w:unhideWhenUsed=\"0\"/>\n" +
            "            <w:lsdException w:name=\"Revision\" w:unhideWhenUsed=\"0\"/>\n" +
            "            <w:lsdException w:name=\"List Paragraph\" w:semiHidden=\"0\" w:uiPriority=\"34\" w:unhideWhenUsed=\"0\"\n" +
            "                            w:qFormat=\"1\"/>\n" +
            "            <w:lsdException w:name=\"Quote\" w:semiHidden=\"0\" w:uiPriority=\"29\" w:unhideWhenUsed=\"0\"\n" +
            "                            w:qFormat=\"1\"/>\n" +
            "            <w:lsdException w:name=\"Intense Quote\" w:semiHidden=\"0\" w:uiPriority=\"30\" w:unhideWhenUsed=\"0\"\n" +
            "                            w:qFormat=\"1\"/>\n" +
            "            <w:lsdException w:name=\"Medium List 2 Accent 1\" w:semiHidden=\"0\" w:uiPriority=\"66\"\n" +
            "                            w:unhideWhenUsed=\"0\"/>\n" +
            "            <w:lsdException w:name=\"Medium Grid 1 Accent 1\" w:semiHidden=\"0\" w:uiPriority=\"67\"\n" +
            "                            w:unhideWhenUsed=\"0\"/>\n" +
            "            <w:lsdException w:name=\"Medium Grid 2 Accent 1\" w:semiHidden=\"0\" w:uiPriority=\"68\"\n" +
            "                            w:unhideWhenUsed=\"0\"/>\n" +
            "            <w:lsdException w:name=\"Medium Grid 3 Accent 1\" w:semiHidden=\"0\" w:uiPriority=\"69\"\n" +
            "                            w:unhideWhenUsed=\"0\"/>\n" +
            "            <w:lsdException w:name=\"Dark List Accent 1\" w:semiHidden=\"0\" w:uiPriority=\"70\"\n" +
            "                            w:unhideWhenUsed=\"0\"/>\n" +
            "            <w:lsdException w:name=\"Colorful Shading Accent 1\" w:semiHidden=\"0\" w:uiPriority=\"71\"\n" +
            "                            w:unhideWhenUsed=\"0\"/>\n" +
            "            <w:lsdException w:name=\"Colorful List Accent 1\" w:semiHidden=\"0\" w:uiPriority=\"72\"\n" +
            "                            w:unhideWhenUsed=\"0\"/>\n" +
            "            <w:lsdException w:name=\"Colorful Grid Accent 1\" w:semiHidden=\"0\" w:uiPriority=\"73\"\n" +
            "                            w:unhideWhenUsed=\"0\"/>\n" +
            "            <w:lsdException w:name=\"Light Shading Accent 2\" w:semiHidden=\"0\" w:uiPriority=\"60\"\n" +
            "                            w:unhideWhenUsed=\"0\"/>\n" +
            "            <w:lsdException w:name=\"Light List Accent 2\" w:semiHidden=\"0\" w:uiPriority=\"61\"\n" +
            "                            w:unhideWhenUsed=\"0\"/>\n" +
            "            <w:lsdException w:name=\"Light Grid Accent 2\" w:semiHidden=\"0\" w:uiPriority=\"62\"\n" +
            "                            w:unhideWhenUsed=\"0\"/>\n" +
            "            <w:lsdException w:name=\"Medium Shading 1 Accent 2\" w:semiHidden=\"0\" w:uiPriority=\"63\"\n" +
            "                            w:unhideWhenUsed=\"0\"/>\n" +
            "            <w:lsdException w:name=\"Medium Shading 2 Accent 2\" w:semiHidden=\"0\" w:uiPriority=\"64\"\n" +
            "                            w:unhideWhenUsed=\"0\"/>\n" +
            "            <w:lsdException w:name=\"Medium List 1 Accent 2\" w:semiHidden=\"0\" w:uiPriority=\"65\"\n" +
            "                            w:unhideWhenUsed=\"0\"/>\n" +
            "            <w:lsdException w:name=\"Medium List 2 Accent 2\" w:semiHidden=\"0\" w:uiPriority=\"66\"\n" +
            "                            w:unhideWhenUsed=\"0\"/>\n" +
            "            <w:lsdException w:name=\"Medium Grid 1 Accent 2\" w:semiHidden=\"0\" w:uiPriority=\"67\"\n" +
            "                            w:unhideWhenUsed=\"0\"/>\n" +
            "            <w:lsdException w:name=\"Medium Grid 2 Accent 2\" w:semiHidden=\"0\" w:uiPriority=\"68\"\n" +
            "                            w:unhideWhenUsed=\"0\"/>\n" +
            "            <w:lsdException w:name=\"Medium Grid 3 Accent 2\" w:semiHidden=\"0\" w:uiPriority=\"69\"\n" +
            "                            w:unhideWhenUsed=\"0\"/>\n" +
            "            <w:lsdException w:name=\"Dark List Accent 2\" w:semiHidden=\"0\" w:uiPriority=\"70\"\n" +
            "                            w:unhideWhenUsed=\"0\"/>\n" +
            "            <w:lsdException w:name=\"Colorful Shading Accent 2\" w:semiHidden=\"0\" w:uiPriority=\"71\"\n" +
            "                            w:unhideWhenUsed=\"0\"/>\n" +
            "            <w:lsdException w:name=\"Colorful List Accent 2\" w:semiHidden=\"0\" w:uiPriority=\"72\"\n" +
            "                            w:unhideWhenUsed=\"0\"/>\n" +
            "            <w:lsdException w:name=\"Colorful Grid Accent 2\" w:semiHidden=\"0\" w:uiPriority=\"73\"\n" +
            "                            w:unhideWhenUsed=\"0\"/>\n" +
            "            <w:lsdException w:name=\"Light Shading Accent 3\" w:semiHidden=\"0\" w:uiPriority=\"60\"\n" +
            "                            w:unhideWhenUsed=\"0\"/>\n" +
            "            <w:lsdException w:name=\"Light List Accent 3\" w:semiHidden=\"0\" w:uiPriority=\"61\"\n" +
            "                            w:unhideWhenUsed=\"0\"/>\n" +
            "            <w:lsdException w:name=\"Light Grid Accent 3\" w:semiHidden=\"0\" w:uiPriority=\"62\"\n" +
            "                            w:unhideWhenUsed=\"0\"/>\n" +
            "            <w:lsdException w:name=\"Medium Shading 1 Accent 3\" w:semiHidden=\"0\" w:uiPriority=\"63\"\n" +
            "                            w:unhideWhenUsed=\"0\"/>\n" +
            "            <w:lsdException w:name=\"Medium Shading 2 Accent 3\" w:semiHidden=\"0\" w:uiPriority=\"64\"\n" +
            "                            w:unhideWhenUsed=\"0\"/>\n" +
            "            <w:lsdException w:name=\"Medium List 1 Accent 3\" w:semiHidden=\"0\" w:uiPriority=\"65\"\n" +
            "                            w:unhideWhenUsed=\"0\"/>\n" +
            "            <w:lsdException w:name=\"Medium List 2 Accent 3\" w:semiHidden=\"0\" w:uiPriority=\"66\"\n" +
            "                            w:unhideWhenUsed=\"0\"/>\n" +
            "            <w:lsdException w:name=\"Medium Grid 1 Accent 3\" w:semiHidden=\"0\" w:uiPriority=\"67\"\n" +
            "                            w:unhideWhenUsed=\"0\"/>\n" +
            "            <w:lsdException w:name=\"Medium Grid 2 Accent 3\" w:semiHidden=\"0\" w:uiPriority=\"68\"\n" +
            "                            w:unhideWhenUsed=\"0\"/>\n" +
            "            <w:lsdException w:name=\"Medium Grid 3 Accent 3\" w:semiHidden=\"0\" w:uiPriority=\"69\"\n" +
            "                            w:unhideWhenUsed=\"0\"/>\n" +
            "            <w:lsdException w:name=\"Dark List Accent 3\" w:semiHidden=\"0\" w:uiPriority=\"70\"\n" +
            "                            w:unhideWhenUsed=\"0\"/>\n" +
            "            <w:lsdException w:name=\"Colorful Shading Accent 3\" w:semiHidden=\"0\" w:uiPriority=\"71\"\n" +
            "                            w:unhideWhenUsed=\"0\"/>\n" +
            "            <w:lsdException w:name=\"Colorful List Accent 3\" w:semiHidden=\"0\" w:uiPriority=\"72\"\n" +
            "                            w:unhideWhenUsed=\"0\"/>\n" +
            "            <w:lsdException w:name=\"Colorful Grid Accent 3\" w:semiHidden=\"0\" w:uiPriority=\"73\"\n" +
            "                            w:unhideWhenUsed=\"0\"/>\n" +
            "            <w:lsdException w:name=\"Light Shading Accent 4\" w:semiHidden=\"0\" w:uiPriority=\"60\"\n" +
            "                            w:unhideWhenUsed=\"0\"/>\n" +
            "            <w:lsdException w:name=\"Light List Accent 4\" w:semiHidden=\"0\" w:uiPriority=\"61\"\n" +
            "                            w:unhideWhenUsed=\"0\"/>\n" +
            "            <w:lsdException w:name=\"Light Grid Accent 4\" w:semiHidden=\"0\" w:uiPriority=\"62\"\n" +
            "                            w:unhideWhenUsed=\"0\"/>\n" +
            "            <w:lsdException w:name=\"Medium Shading 1 Accent 4\" w:semiHidden=\"0\" w:uiPriority=\"63\"\n" +
            "                            w:unhideWhenUsed=\"0\"/>\n" +
            "            <w:lsdException w:name=\"Medium Shading 2 Accent 4\" w:semiHidden=\"0\" w:uiPriority=\"64\"\n" +
            "                            w:unhideWhenUsed=\"0\"/>\n" +
            "            <w:lsdException w:name=\"Medium List 1 Accent 4\" w:semiHidden=\"0\" w:uiPriority=\"65\"\n" +
            "                            w:unhideWhenUsed=\"0\"/>\n" +
            "            <w:lsdException w:name=\"Medium List 2 Accent 4\" w:semiHidden=\"0\" w:uiPriority=\"66\"\n" +
            "                            w:unhideWhenUsed=\"0\"/>\n" +
            "            <w:lsdException w:name=\"Medium Grid 1 Accent 4\" w:semiHidden=\"0\" w:uiPriority=\"67\"\n" +
            "                            w:unhideWhenUsed=\"0\"/>\n" +
            "            <w:lsdException w:name=\"Medium Grid 2 Accent 4\" w:semiHidden=\"0\" w:uiPriority=\"68\"\n" +
            "                            w:unhideWhenUsed=\"0\"/>\n" +
            "            <w:lsdException w:name=\"Medium Grid 3 Accent 4\" w:semiHidden=\"0\" w:uiPriority=\"69\"\n" +
            "                            w:unhideWhenUsed=\"0\"/>\n" +
            "            <w:lsdException w:name=\"Dark List Accent 4\" w:semiHidden=\"0\" w:uiPriority=\"70\"\n" +
            "                            w:unhideWhenUsed=\"0\"/>\n" +
            "            <w:lsdException w:name=\"Colorful Shading Accent 4\" w:semiHidden=\"0\" w:uiPriority=\"71\"\n" +
            "                            w:unhideWhenUsed=\"0\"/>\n" +
            "            <w:lsdException w:name=\"Colorful List Accent 4\" w:semiHidden=\"0\" w:uiPriority=\"72\"\n" +
            "                            w:unhideWhenUsed=\"0\"/>\n" +
            "            <w:lsdException w:name=\"Colorful Grid Accent 4\" w:semiHidden=\"0\" w:uiPriority=\"73\"\n" +
            "                            w:unhideWhenUsed=\"0\"/>\n" +
            "            <w:lsdException w:name=\"Light Shading Accent 5\" w:semiHidden=\"0\" w:uiPriority=\"60\"\n" +
            "                            w:unhideWhenUsed=\"0\"/>\n" +
            "            <w:lsdException w:name=\"Light List Accent 5\" w:semiHidden=\"0\" w:uiPriority=\"61\"\n" +
            "                            w:unhideWhenUsed=\"0\"/>\n" +
            "            <w:lsdException w:name=\"Light Grid Accent 5\" w:semiHidden=\"0\" w:uiPriority=\"62\"\n" +
            "                            w:unhideWhenUsed=\"0\"/>\n" +
            "            <w:lsdException w:name=\"Medium Shading 1 Accent 5\" w:semiHidden=\"0\" w:uiPriority=\"63\"\n" +
            "                            w:unhideWhenUsed=\"0\"/>\n" +
            "            <w:lsdException w:name=\"Medium Shading 2 Accent 5\" w:semiHidden=\"0\" w:uiPriority=\"64\"\n" +
            "                            w:unhideWhenUsed=\"0\"/>\n" +
            "            <w:lsdException w:name=\"Medium List 1 Accent 5\" w:semiHidden=\"0\" w:uiPriority=\"65\"\n" +
            "                            w:unhideWhenUsed=\"0\"/>\n" +
            "            <w:lsdException w:name=\"Medium List 2 Accent 5\" w:semiHidden=\"0\" w:uiPriority=\"66\"\n" +
            "                            w:unhideWhenUsed=\"0\"/>\n" +
            "            <w:lsdException w:name=\"Medium Grid 1 Accent 5\" w:semiHidden=\"0\" w:uiPriority=\"67\"\n" +
            "                            w:unhideWhenUsed=\"0\"/>\n" +
            "            <w:lsdException w:name=\"Medium Grid 2 Accent 5\" w:semiHidden=\"0\" w:uiPriority=\"68\"\n" +
            "                            w:unhideWhenUsed=\"0\"/>\n" +
            "            <w:lsdException w:name=\"Medium Grid 3 Accent 5\" w:semiHidden=\"0\" w:uiPriority=\"69\"\n" +
            "                            w:unhideWhenUsed=\"0\"/>\n" +
            "            <w:lsdException w:name=\"Dark List Accent 5\" w:semiHidden=\"0\" w:uiPriority=\"70\"\n" +
            "                            w:unhideWhenUsed=\"0\"/>\n" +
            "            <w:lsdException w:name=\"Colorful Shading Accent 5\" w:semiHidden=\"0\" w:uiPriority=\"71\"\n" +
            "                            w:unhideWhenUsed=\"0\"/>\n" +
            "            <w:lsdException w:name=\"Colorful List Accent 5\" w:semiHidden=\"0\" w:uiPriority=\"72\"\n" +
            "                            w:unhideWhenUsed=\"0\"/>\n" +
            "            <w:lsdException w:name=\"Colorful Grid Accent 5\" w:semiHidden=\"0\" w:uiPriority=\"73\"\n" +
            "                            w:unhideWhenUsed=\"0\"/>\n" +
            "            <w:lsdException w:name=\"Light Shading Accent 6\" w:semiHidden=\"0\" w:uiPriority=\"60\"\n" +
            "                            w:unhideWhenUsed=\"0\"/>\n" +
            "            <w:lsdException w:name=\"Light List Accent 6\" w:semiHidden=\"0\" w:uiPriority=\"61\"\n" +
            "                            w:unhideWhenUsed=\"0\"/>\n" +
            "            <w:lsdException w:name=\"Light Grid Accent 6\" w:semiHidden=\"0\" w:uiPriority=\"62\"\n" +
            "                            w:unhideWhenUsed=\"0\"/>\n" +
            "            <w:lsdException w:name=\"Medium Shading 1 Accent 6\" w:semiHidden=\"0\" w:uiPriority=\"63\"\n" +
            "                            w:unhideWhenUsed=\"0\"/>\n" +
            "            <w:lsdException w:name=\"Medium Shading 2 Accent 6\" w:semiHidden=\"0\" w:uiPriority=\"64\"\n" +
            "                            w:unhideWhenUsed=\"0\"/>\n" +
            "            <w:lsdException w:name=\"Medium List 1 Accent 6\" w:semiHidden=\"0\" w:uiPriority=\"65\"\n" +
            "                            w:unhideWhenUsed=\"0\"/>\n" +
            "            <w:lsdException w:name=\"Medium List 2 Accent 6\" w:semiHidden=\"0\" w:uiPriority=\"66\"\n" +
            "                            w:unhideWhenUsed=\"0\"/>\n" +
            "            <w:lsdException w:name=\"Medium Grid 1 Accent 6\" w:semiHidden=\"0\" w:uiPriority=\"67\"\n" +
            "                            w:unhideWhenUsed=\"0\"/>\n" +
            "            <w:lsdException w:name=\"Medium Grid 2 Accent 6\" w:semiHidden=\"0\" w:uiPriority=\"68\"\n" +
            "                            w:unhideWhenUsed=\"0\"/>\n" +
            "            <w:lsdException w:name=\"Medium Grid 3 Accent 6\" w:semiHidden=\"0\" w:uiPriority=\"69\"\n" +
            "                            w:unhideWhenUsed=\"0\"/>\n" +
            "            <w:lsdException w:name=\"Dark List Accent 6\" w:semiHidden=\"0\" w:uiPriority=\"70\"\n" +
            "                            w:unhideWhenUsed=\"0\"/>\n" +
            "            <w:lsdException w:name=\"Colorful Shading Accent 6\" w:semiHidden=\"0\" w:uiPriority=\"71\"\n" +
            "                            w:unhideWhenUsed=\"0\"/>\n" +
            "            <w:lsdException w:name=\"Colorful List Accent 6\" w:semiHidden=\"0\" w:uiPriority=\"72\"\n" +
            "                            w:unhideWhenUsed=\"0\"/>\n" +
            "            <w:lsdException w:name=\"Colorful Grid Accent 6\" w:semiHidden=\"0\" w:uiPriority=\"73\"\n" +
            "                            w:unhideWhenUsed=\"0\"/>\n" +
            "            <w:lsdException w:name=\"Subtle Emphasis\" w:semiHidden=\"0\" w:uiPriority=\"19\" w:unhideWhenUsed=\"0\"\n" +
            "                            w:qFormat=\"1\"/>\n" +
            "            <w:lsdException w:name=\"Intense Emphasis\" w:semiHidden=\"0\" w:uiPriority=\"21\" w:unhideWhenUsed=\"0\"\n" +
            "                            w:qFormat=\"1\"/>\n" +
            "            <w:lsdException w:name=\"Subtle Reference\" w:semiHidden=\"0\" w:uiPriority=\"31\" w:unhideWhenUsed=\"0\"\n" +
            "                            w:qFormat=\"1\"/>\n" +
            "            <w:lsdException w:name=\"Intense Reference\" w:semiHidden=\"0\" w:uiPriority=\"32\" w:unhideWhenUsed=\"0\"\n" +
            "                            w:qFormat=\"1\"/>\n" +
            "            <w:lsdException w:name=\"Book Title\" w:semiHidden=\"0\" w:uiPriority=\"33\" w:unhideWhenUsed=\"0\"\n" +
            "                            w:qFormat=\"1\"/>\n" +
            "            <w:lsdException w:name=\"Bibliography\" w:uiPriority=\"37\"/>\n" +
            "            <w:lsdException w:name=\"TOC Heading\" w:uiPriority=\"39\" w:qFormat=\"1\"/>\n" +
            "        </w:latentStyles>\n" +
            "        <w:style w:type=\"paragraph\" w:default=\"1\" w:styleId=\"a\">\n" +
            "            <w:name w:val=\"Normal\"/>\n" +
            "            <w:qFormat/>\n" +
            "            <w:rsid w:val=\"00323B43\"/>\n" +
            "            <w:pPr>\n" +
            "                <w:adjustRightInd w:val=\"0\"/>\n" +
            "                <w:snapToGrid w:val=\"0\"/>\n" +
            "                <w:spacing w:line=\"240\" w:lineRule=\"auto\"/>\n" +
            "            </w:pPr>\n" +
            "            <w:rPr>\n" +
            "                <w:rFonts w:ascii=\"Tahoma\" w:hAnsi=\"Tahoma\"/>\n" +
            "            </w:rPr>\n" +
            "        </w:style>\n" +
            "        <w:style w:type=\"character\" w:default=\"1\" w:styleId=\"a0\">\n" +
            "            <w:name w:val=\"Default Paragraph Font\"/>\n" +
            "            <w:uiPriority w:val=\"1\"/>\n" +
            "            <w:semiHidden/>\n" +
            "            <w:unhideWhenUsed/>\n" +
            "        </w:style>\n" +
            "        <w:style w:type=\"table\" w:default=\"1\" w:styleId=\"a1\">\n" +
            "            <w:name w:val=\"Normal Table\"/>\n" +
            "            <w:uiPriority w:val=\"99\"/>\n" +
            "            <w:semiHidden/>\n" +
            "            <w:unhideWhenUsed/>\n" +
            "            <w:qFormat/>\n" +
            "            <w:tblPr>\n" +
            "                <w:tblInd w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                <w:tblCellMar>\n" +
            "                    <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    <w:left w:w=\"108\" w:type=\"dxa\"/>\n" +
            "                    <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    <w:right w:w=\"108\" w:type=\"dxa\"/>\n" +
            "                </w:tblCellMar>\n" +
            "            </w:tblPr>\n" +
            "        </w:style>\n" +
            "        <w:style w:type=\"numbering\" w:default=\"1\" w:styleId=\"a2\">\n" +
            "            <w:name w:val=\"No List\"/>\n" +
            "            <w:uiPriority w:val=\"99\"/>\n" +
            "            <w:semiHidden/>\n" +
            "            <w:unhideWhenUsed/>\n" +
            "        </w:style>\n" +
            "        <w:style w:type=\"table\" w:styleId=\"a3\">\n" +
            "            <w:name w:val=\"Table Grid\"/>\n" +
            "            <w:basedOn w:val=\"a1\"/>\n" +
            "            <w:uiPriority w:val=\"59\"/>\n" +
            "            <w:rsid w:val=\"00C749FF\"/>\n" +
            "            <w:pPr>\n" +
            "                <w:spacing w:after=\"0\" w:line=\"240\" w:lineRule=\"auto\"/>\n" +
            "            </w:pPr>\n" +
            "            <w:tblPr>\n" +
            "                <w:tblInd w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                <w:tblBorders>\n" +
            "                    <w:top w:val=\"single\" w:sz=\"4\" w:space=\"0\" w:color=\"auto\"/>\n" +
            "                    <w:left w:val=\"single\" w:sz=\"4\" w:space=\"0\" w:color=\"auto\"/>\n" +
            "                    <w:bottom w:val=\"single\" w:sz=\"4\" w:space=\"0\" w:color=\"auto\"/>\n" +
            "                    <w:right w:val=\"single\" w:sz=\"4\" w:space=\"0\" w:color=\"auto\"/>\n" +
            "                    <w:insideH w:val=\"single\" w:sz=\"4\" w:space=\"0\" w:color=\"auto\"/>\n" +
            "                    <w:insideV w:val=\"single\" w:sz=\"4\" w:space=\"0\" w:color=\"auto\"/>\n" +
            "                </w:tblBorders>\n" +
            "                <w:tblCellMar>\n" +
            "                    <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    <w:left w:w=\"108\" w:type=\"dxa\"/>\n" +
            "                    <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    <w:right w:w=\"108\" w:type=\"dxa\"/>\n" +
            "                </w:tblCellMar>\n" +
            "            </w:tblPr>\n" +
            "        </w:style>\n" +
            "        <w:style w:type=\"paragraph\" w:styleId=\"a4\">\n" +
            "            <w:name w:val=\"header\"/>\n" +
            "            <w:basedOn w:val=\"a\"/>\n" +
            "            <w:link w:val=\"Char\"/>\n" +
            "            <w:uiPriority w:val=\"99\"/>\n" +
            "            <w:semiHidden/>\n" +
            "            <w:unhideWhenUsed/>\n" +
            "            <w:rsid w:val=\"00AD08ED\"/>\n" +
            "            <w:pPr>\n" +
            "                <w:pBdr>\n" +
            "                    <w:bottom w:val=\"single\" w:sz=\"6\" w:space=\"1\" w:color=\"auto\"/>\n" +
            "                </w:pBdr>\n" +
            "                <w:tabs>\n" +
            "                    <w:tab w:val=\"center\" w:pos=\"4153\"/>\n" +
            "                    <w:tab w:val=\"right\" w:pos=\"8306\"/>\n" +
            "                </w:tabs>\n" +
            "                <w:jc w:val=\"center\"/>\n" +
            "            </w:pPr>\n" +
            "            <w:rPr>\n" +
            "                <w:sz w:val=\"18\"/>\n" +
            "                <w:szCs w:val=\"18\"/>\n" +
            "            </w:rPr>\n" +
            "        </w:style>\n" +
            "        <w:style w:type=\"character\" w:customStyle=\"1\" w:styleId=\"Char\">\n" +
            "            <w:name w:val=\"页眉 Char\"/>\n" +
            "            <w:basedOn w:val=\"a0\"/>\n" +
            "            <w:link w:val=\"a4\"/>\n" +
            "            <w:uiPriority w:val=\"99\"/>\n" +
            "            <w:semiHidden/>\n" +
            "            <w:rsid w:val=\"00AD08ED\"/>\n" +
            "            <w:rPr>\n" +
            "                <w:rFonts w:ascii=\"Tahoma\" w:hAnsi=\"Tahoma\"/>\n" +
            "                <w:sz w:val=\"18\"/>\n" +
            "                <w:szCs w:val=\"18\"/>\n" +
            "            </w:rPr>\n" +
            "        </w:style>\n" +
            "        <w:style w:type=\"paragraph\" w:styleId=\"a5\">\n" +
            "            <w:name w:val=\"footer\"/>\n" +
            "            <w:basedOn w:val=\"a\"/>\n" +
            "            <w:link w:val=\"Char0\"/>\n" +
            "            <w:uiPriority w:val=\"99\"/>\n" +
            "            <w:semiHidden/>\n" +
            "            <w:unhideWhenUsed/>\n" +
            "            <w:rsid w:val=\"00AD08ED\"/>\n" +
            "            <w:pPr>\n" +
            "                <w:tabs>\n" +
            "                    <w:tab w:val=\"center\" w:pos=\"4153\"/>\n" +
            "                    <w:tab w:val=\"right\" w:pos=\"8306\"/>\n" +
            "                </w:tabs>\n" +
            "            </w:pPr>\n" +
            "            <w:rPr>\n" +
            "                <w:sz w:val=\"18\"/>\n" +
            "                <w:szCs w:val=\"18\"/>\n" +
            "            </w:rPr>\n" +
            "        </w:style>\n" +
            "        <w:style w:type=\"character\" w:customStyle=\"1\" w:styleId=\"Char0\">\n" +
            "            <w:name w:val=\"页脚 Char\"/>\n" +
            "            <w:basedOn w:val=\"a0\"/>\n" +
            "            <w:link w:val=\"a5\"/>\n" +
            "            <w:uiPriority w:val=\"99\"/>\n" +
            "            <w:semiHidden/>\n" +
            "            <w:rsid w:val=\"00AD08ED\"/>\n" +
            "            <w:rPr>\n" +
            "                <w:rFonts w:ascii=\"Tahoma\" w:hAnsi=\"Tahoma\"/>\n" +
            "                <w:sz w:val=\"18\"/>\n" +
            "                <w:szCs w:val=\"18\"/>\n" +
            "            </w:rPr>\n" +
            "        </w:style>\n" +
            "    </w:styles>\n" +
            "</pkg:xmlData>\n" +
            "</pkg:part>\n" +
            "<pkg:part pkg:name=\"/word/fontTable.xml\"\n" +
            "          pkg:contentType=\"application/vnd.openxmlformats-officedocument.wordprocessingml.fontTable+xml\">\n" +
            "<pkg:xmlData>\n" +
            "    <w:fonts xmlns:r=\"http://schemas.openxmlformats.org/officeDocument/2006/relationships\"\n" +
            "             xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\">\n" +
            "        <w:font w:name=\"Calibri\">\n" +
            "            <w:panose1 w:val=\"020F0502020204030204\"/>\n" +
            "            <w:charset w:val=\"00\"/>\n" +
            "            <w:family w:val=\"swiss\"/>\n" +
            "            <w:pitch w:val=\"variable\"/>\n" +
            "            <w:sig w:usb0=\"E10002FF\" w:usb1=\"4000ACFF\" w:usb2=\"00000009\" w:usb3=\"00000000\" w:csb0=\"0000019F\"\n" +
            "                   w:csb1=\"00000000\"/>\n" +
            "        </w:font>\n" +
            "        <w:font w:name=\"微软雅黑\">\n" +
            "            <w:panose1 w:val=\"020B0503020204020204\"/>\n" +
            "            <w:charset w:val=\"86\"/>\n" +
            "            <w:family w:val=\"swiss\"/>\n" +
            "            <w:pitch w:val=\"variable\"/>\n" +
            "            <w:sig w:usb0=\"80000287\" w:usb1=\"280F3C52\" w:usb2=\"00000016\" w:usb3=\"00000000\" w:csb0=\"0004001F\"\n" +
            "                   w:csb1=\"00000000\"/>\n" +
            "        </w:font>\n" +
            "        <w:font w:name=\"Times New Roman\">\n" +
            "            <w:panose1 w:val=\"02020603050405020304\"/>\n" +
            "            <w:charset w:val=\"00\"/>\n" +
            "            <w:family w:val=\"roman\"/>\n" +
            "            <w:pitch w:val=\"variable\"/>\n" +
            "            <w:sig w:usb0=\"E0002AFF\" w:usb1=\"C0007841\" w:usb2=\"00000009\" w:usb3=\"00000000\" w:csb0=\"000001FF\"\n" +
            "                   w:csb1=\"00000000\"/>\n" +
            "        </w:font>\n" +
            "        <w:font w:name=\"Tahoma\">\n" +
            "            <w:panose1 w:val=\"020B0604030504040204\"/>\n" +
            "            <w:charset w:val=\"00\"/>\n" +
            "            <w:family w:val=\"swiss\"/>\n" +
            "            <w:pitch w:val=\"variable\"/>\n" +
            "            <w:sig w:usb0=\"E1002EFF\" w:usb1=\"C000605B\" w:usb2=\"00000029\" w:usb3=\"00000000\" w:csb0=\"000101FF\"\n" +
            "                   w:csb1=\"00000000\"/>\n" +
            "        </w:font>\n" +
            "        <w:font w:name=\"宋体\">\n" +
            "            <w:altName w:val=\"SimSun\"/>\n" +
            "            <w:panose1 w:val=\"02010600030101010101\"/>\n" +
            "            <w:charset w:val=\"86\"/>\n" +
            "            <w:family w:val=\"auto\"/>\n" +
            "            <w:pitch w:val=\"variable\"/>\n" +
            "            <w:sig w:usb0=\"00000003\" w:usb1=\"288F0000\" w:usb2=\"00000016\" w:usb3=\"00000000\" w:csb0=\"00040001\"\n" +
            "                   w:csb1=\"00000000\"/>\n" +
            "        </w:font>\n" +
            "        <w:font w:name=\"Cambria\">\n" +
            "            <w:panose1 w:val=\"02040503050406030204\"/>\n" +
            "            <w:charset w:val=\"00\"/>\n" +
            "            <w:family w:val=\"roman\"/>\n" +
            "            <w:pitch w:val=\"variable\"/>\n" +
            "            <w:sig w:usb0=\"E00002FF\" w:usb1=\"400004FF\" w:usb2=\"00000000\" w:usb3=\"00000000\" w:csb0=\"0000019F\"\n" +
            "                   w:csb1=\"00000000\"/>\n" +
            "        </w:font>\n" +
            "    </w:fonts>\n" +
            "</pkg:xmlData>\n" +
            "</pkg:part>\n" +
            "<pkg:part pkg:name=\"/docProps/app.xml\"\n" +
            "          pkg:contentType=\"application/vnd.openxmlformats-officedocument.extended-properties+xml\" pkg:padding=\"256\">\n" +
            "<pkg:xmlData>\n" +
            "    <Properties xmlns=\"http://schemas.openxmlformats.org/officeDocument/2006/extended-properties\"\n" +
            "                xmlns:vt=\"http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes\">\n" +
            "        <Template>Normal.dotm</Template>\n" +
            "        <TotalTime>4</TotalTime>\n" +
            "        <Pages>1</Pages>\n" +
            "        <Words>41</Words>\n" +
            "        <Characters>237</Characters>\n" +
            "        <Application>Microsoft Office Word</Application>\n" +
            "        <DocSecurity>0</DocSecurity>\n" +
            "        <Lines>1</Lines>\n" +
            "        <Paragraphs>1</Paragraphs>\n" +
            "        <ScaleCrop>false</ScaleCrop>\n" +
            "        <Company/>\n" +
            "        <LinksUpToDate>false</LinksUpToDate>\n" +
            "        <CharactersWithSpaces>277</CharactersWithSpaces>\n" +
            "        <SharedDoc>false</SharedDoc>\n" +
            "        <HyperlinksChanged>false</HyperlinksChanged>\n" +
            "        <AppVersion>12.0000</AppVersion>\n" +
            "    </Properties>\n" +
            "</pkg:xmlData>\n" +
            "</pkg:part>\n" +
            "        </pkg:package>";
}
