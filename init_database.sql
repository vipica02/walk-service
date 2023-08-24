-- SUPPLIER: table
create table UNIFORM_WATER.SUPPLIER
(
    ID                   VARCHAR2(50)  not null
        primary key,
    SUPPLIER_NAME        VARCHAR2(100) not null,
    SUPPLIER_CODE        VARCHAR2(100) not null,
    SUPPLIER_AUTH_METHOD VARCHAR2(100),
    DOMAIN               VARCHAR2(100),
    SERVICE_TYPE         VARCHAR2(100) not null,
    API_DATA_FORMAT      VARCHAR2(100),
    DESCRIPTION          VARCHAR2(1000),
    ADDRESS              VARCHAR2(1000),
    IP                   VARCHAR2(100),
    STATUS               VARCHAR2(100) not null,
    SUPPLIER_METADATA    CLOB          not null,
    CREATED_BY           VARCHAR2(100),
    CREATED_DATE         TIMESTAMP(6),
    LAST_MODIFIED_BY     VARCHAR2(100),
    LAST_MODIFIED_DATE   TIMESTAMP(6)
)
/

comment on column UNIFORM_WATER.SUPPLIER.SUPPLIER_NAME is 'Tên nhà cung cấp'
/

comment on column UNIFORM_WATER.SUPPLIER.SUPPLIER_CODE is 'Mã nhà cung cấp'
/

comment on column UNIFORM_WATER.SUPPLIER.SERVICE_TYPE is 'Loại dịch vụ (WATER_SERVICE, TIN_SERVICE,...)'
/

comment on column UNIFORM_WATER.SUPPLIER.ADDRESS is 'Địa chỉ nhà cung cấp'
/

comment on column UNIFORM_WATER.SUPPLIER.STATUS is 'Trạng thái  ACTIVE, INACTIVE'
/

comment on column UNIFORM_WATER.SUPPLIER.SUPPLIER_METADATA is 'meta data tùy theo loại nhà cung cấp sẽ có metadata khác nhau'
/


-- PAYMENT_RULE: table
create table UNIFORM_WATER.PAYMENT_RULE
(
    ID                    VARCHAR2(50)  not null
        constraint PAYMENT_RULE_PK
            primary key,
    NAME                  VARCHAR2(100) not null,
    CODE                  VARCHAR2(100) not null,
    DESCRIPTION           VARCHAR2(1000),
    STATUS                VARCHAR2(100) not null,
    PAYMENT_FORM_GROUP_ID VARCHAR2(50)  not null,
    IP                    VARCHAR2(100),
    CREATED_BY            VARCHAR2(100),
    CREATED_DATE          TIMESTAMP(6),
    LAST_MODIFIED_BY      VARCHAR2(100),
    LAST_MODIFIED_DATE    TIMESTAMP(6)
)
/

comment on column UNIFORM_WATER.PAYMENT_RULE.CODE is 'Mã quy tắc thanh toán'
/

comment on column UNIFORM_WATER.PAYMENT_RULE.STATUS is 'ACTIVE, INACTIVE'
/

comment on column UNIFORM_WATER.PAYMENT_RULE.PAYMENT_FORM_GROUP_ID is 'Id nhóm quy tắc thanh toán'
/



-- PAYMENT_FORM_GROUP: table
create table UNIFORM_WATER.PAYMENT_FORM_GROUP
(
    ID                 VARCHAR2(50)  not null
        constraint PAYMENT_FORM_GROUP_PK
        primary key,
    NAME               VARCHAR2(100) not null,
    CODE               VARCHAR2(100) not null,
    DESCRIPTION        VARCHAR2(1000),
    STATUS             VARCHAR2(100) not null,
    SERVICE_TYPE       VARCHAR2(100) not null,
    TYPE               VARCHAR2(100) not null,
    IP                 VARCHAR2(100),
    CREATED_BY         VARCHAR2(100),
    CREATED_DATE       TIMESTAMP(6),
    LAST_MODIFIED_BY   VARCHAR2(100),
    LAST_MODIFIED_DATE TIMESTAMP(6)
)
    /

comment on column UNIFORM_WATER.PAYMENT_FORM_GROUP.CODE is 'mã nhóm quy tắc thanh toán'
/

comment on column UNIFORM_WATER.PAYMENT_FORM_GROUP.STATUS is 'trạng thái ACTIVE, INACTIVE'
/

comment on column UNIFORM_WATER.PAYMENT_FORM_GROUP.SERVICE_TYPE is 'Loại dịch vụ'
/

comment on column UNIFORM_WATER.PAYMENT_FORM_GROUP.TYPE is 'DEFAULT, CUSTOM'
/

-- SUPPLIER_FORM_GROUP: table
create table UNIFORM_WATER.SUPPLIER_FORM_GROUP
(
    ID                    VARCHAR2(50) not null
        constraint "SUPPLIER_FORM_GROUP_pk"
            primary key,
    SUPPLIER_ID           VARCHAR2(50) not null,
    PAYMENT_FORM_GROUP_ID VARCHAR2(50) not null,
    CREATED_BY            VARCHAR2(100),
    CREATED_DATE          TIMESTAMP(6),
    LAST_MODIFIED_BY      VARCHAR2(100),
    LAST_MODIFIED_DATE    TIMESTAMP(6)
)
/

comment on column UNIFORM_WATER.SUPPLIER_FORM_GROUP.SUPPLIER_ID is 'ID nhà cung cấp'
/

comment on column UNIFORM_WATER.SUPPLIER_FORM_GROUP.PAYMENT_FORM_GROUP_ID is 'ID Nhóm quy tắc thanh toán'
/

-- SUPPLIER_RULE: table
create table UNIFORM_WATER.SUPPLIER_RULE
(
    ID                 VARCHAR2(50) not null
        constraint SUPPLIER_RULE_PK
            primary key,
    SUPPLIER_ID        VARCHAR2(50) not null,
    PAYMENT_RULE_ID    VARCHAR2(50) not null,
    CREATED_BY         VARCHAR2(100),
    CREATED_DATE       TIMESTAMP(6),
    LAST_MODIFIED_BY   VARCHAR2(100),
    LAST_MODIFIED_DATE TIMESTAMP(6)
)
/

comment on column UNIFORM_WATER.SUPPLIER_RULE.SUPPLIER_ID is 'ID nhà cung cấp'
/

comment on column UNIFORM_WATER.SUPPLIER_RULE.PAYMENT_RULE_ID is 'ID quy tắc thanh toán'
/

-- TRANSACTION: table
create table UNIFORM_WATER.TRANSACTION
(
    ID                 VARCHAR2(50)  not null
        constraint PAYMENT_TRANSACTION_PK
            primary key,
    TRN_BRN            VARCHAR2(100),
    TRN_NAME           VARCHAR2(500),
    TRN_DESC           VARCHAR2(500),
    TRN_DT             DATE,
    MODULE             VARCHAR2(20),
    MAKER_ID           VARCHAR2(100),
    MAKER_DT           DATE,
    CHECKER_ID         VARCHAR2(100),
    CHECKER_DT         DATE,
    SUPPLIER_CODE      VARCHAR2(100),
    CIF                VARCHAR2(100),
    PAYMENT_TYPE       VARCHAR2(100),
    TOTAL_AMOUNT       NUMBER,
    CUSTOMER_ID        VARCHAR2(300),
    CUSTOMER_NAME      VARCHAR2(500),
    ACC_NUMBER         VARCHAR2(100),
    ACC_NAME           VARCHAR2(500),
    ACC_BRN            VARCHAR2(100),
    XREF               VARCHAR2(100),
    STATUS             VARCHAR2(100) not null,
    HT_STATUS          VARCHAR2(100) not null,
    GN_STATUS          VARCHAR2(100) not null,
    CREATED_BY         VARCHAR2(100),
    CREATED_DATE       TIMESTAMP(6),
    LAST_MODIFIED_BY   VARCHAR2(100),
    LAST_MODIFIED_DATE TIMESTAMP(6),
    CCY                VARCHAR2(20),
    CUSTOMER_ADDRESS   VARCHAR2(500),
    PAYMENT_CHANNEL    VARCHAR2(50),
    REASON             VARCHAR2(500),
    TRANS_NO           NUMBER default "UNIFORM_WATER"."TRANS_NO_SEQ"."NEXTVAL"
)
/

comment on column UNIFORM_WATER.TRANSACTION.TRN_BRN is 'Mã chi nhánh tạo giao dịch'
/

comment on column UNIFORM_WATER.TRANSACTION.TRN_NAME is 'Tên chi nhánh tạo giao dịch'
/

comment on column UNIFORM_WATER.TRANSACTION.TRN_DESC is 'Mô tả diễn giải'
/

comment on column UNIFORM_WATER.TRANSACTION.TRN_DT is 'Thời gian tạo giao dịch'
/

comment on column UNIFORM_WATER.TRANSACTION.MODULE is 'UNIFORM '
/

comment on column UNIFORM_WATER.TRANSACTION.MAKER_ID is 'ID giao dịch viên tạo giao dịch'
/

comment on column UNIFORM_WATER.TRANSACTION.MAKER_DT is 'Ngày tạo giao dịch'
/

comment on column UNIFORM_WATER.TRANSACTION.CHECKER_ID is 'ID kiểm soát viên duyệt giao dịch'
/

comment on column UNIFORM_WATER.TRANSACTION.SUPPLIER_CODE is 'Mã nhà cung cấp'
/

comment on column UNIFORM_WATER.TRANSACTION.CIF is 'Mã cif '
/

comment on column UNIFORM_WATER.TRANSACTION.PAYMENT_TYPE is 'Hình thức thanh toán ( CK: chuyển khoản, TTTM: thanh toán tiền mặt)'
/

comment on column UNIFORM_WATER.TRANSACTION.TOTAL_AMOUNT is 'Tổng tiền thanh toán'
/

comment on column UNIFORM_WATER.TRANSACTION.CUSTOMER_ID is 'Mã khách hàng'
/

comment on column UNIFORM_WATER.TRANSACTION.CUSTOMER_NAME is 'Tên khách hàng'
/

comment on column UNIFORM_WATER.TRANSACTION.ACC_NUMBER is 'Số tài khoản nhà cung cấp'
/

comment on column UNIFORM_WATER.TRANSACTION.ACC_NAME is 'Tên tài khoản nhà cung cấp'
/

comment on column UNIFORM_WATER.TRANSACTION.ACC_BRN is 'Chi nhánh tài khoản nhà cung cấp'
/

comment on column UNIFORM_WATER.TRANSACTION.STATUS is 'trạng thái giao dịch (IN_PROCESS : chờ duyệt , APPROVE: đã duyệt , DENIED: từ chối, ERROR: Lỗi , CANCEL: hủy)'
/

comment on column UNIFORM_WATER.TRANSACTION.HT_STATUS is 'Trạng thái hạch toán (IN_PROCESS: chờ duyệt, SUCCESS: thành công, FALSE: thất bại , ERROR: lỗi, DENIED: từ chối)'
/

comment on column UNIFORM_WATER.TRANSACTION.GN_STATUS is 'trạng thái gạch nợ (IN_PROCESS: chờ duyệt, SUCCESS: thành công, FALSE: thất bại, ERROR: lỗi)'
/

comment on column UNIFORM_WATER.TRANSACTION.CCY is 'Loại tiền'
/

comment on column UNIFORM_WATER.TRANSACTION.CUSTOMER_ADDRESS is 'Địa chỉ khách hàng'
/

comment on column UNIFORM_WATER.TRANSACTION.PAYMENT_CHANNEL is 'kênh'
/

-- TRANS_POST: table
create table UNIFORM_WATER.TRANS_POST
(
    ID                 VARCHAR2(50) not null
        constraint PAYMENT_TRANSACTION_POST_PK
            primary key,
    TRANS_ID           VARCHAR2(100),
    AC_NUMBER          VARCHAR2(100),
    AC_NAME            VARCHAR2(100),
    AC_BRN             VARCHAR2(100),
    DRCR_IND           VARCHAR2(1),
    LCY_AMOUNT         NUMBER,
    CCY                VARCHAR2(20),
    MAKER_ID           VARCHAR2(100),
    MAKER_DT           DATE,
    AUTH_ID            VARCHAR2(100),
    AUTH_DT            DATE,
    HT_STATUS          VARCHAR2(20) not null,
    CREATED_BY         VARCHAR2(100),
    CREATED_DATE       TIMESTAMP(6),
    LAST_MODIFIED_BY   VARCHAR2(100),
    LAST_MODIFIED_DATE TIMESTAMP(6),
    CORE_REF_NO        VARCHAR2(100),
    CORE_DESC          VARCHAR2(500),
    CORE_DT            TIMESTAMP(6)
)
/

comment on column UNIFORM_WATER.TRANS_POST.TRANS_ID is 'ID giao dịch gốc'
/

comment on column UNIFORM_WATER.TRANS_POST.AC_NUMBER is 'Tài khoản ghi nợ/có '
/

comment on column UNIFORM_WATER.TRANS_POST.AC_NAME is 'Tên tài khoản ghi nợ hoặc ghi có'
/

comment on column UNIFORM_WATER.TRANS_POST.AC_BRN is 'mã chi nhánh của tài khoản ghi nợ hoặc ghi có'
/

comment on column UNIFORM_WATER.TRANS_POST.DRCR_IND is 'Tính chất ghi  nợ /có  (D or C) '
/

comment on column UNIFORM_WATER.TRANS_POST.LCY_AMOUNT is 'Số tiền chuyển giao'
/

comment on column UNIFORM_WATER.TRANS_POST.CCY is 'Loại tiền '
/

comment on column UNIFORM_WATER.TRANS_POST.MAKER_ID is 'giao dịch viên id thực hiện tạo '
/

comment on column UNIFORM_WATER.TRANS_POST.MAKER_DT is 'Ngày tạo'
/

comment on column UNIFORM_WATER.TRANS_POST.AUTH_ID is 'Id Kiểm soát viên duyệt'
/

comment on column UNIFORM_WATER.TRANS_POST.AUTH_DT is 'ngày duyệt'
/

comment on column UNIFORM_WATER.TRANS_POST.HT_STATUS is 'trạng thái hạch toán  (IN_PROCESS, SUCCESS, FALSE, ERROR, DENIED) (chờ duyệt, thành công, thất bại, lỗi, từ chối)'
/

comment on column UNIFORM_WATER.TRANS_POST.CORE_REF_NO is 'CORE_REF_NO (BATCH_NUMBER trả về sau khi hạch toán chuyển khoản thành công) hoặc (TRN_REF_NO trả về khi thực hiện hạch toán tiền mặt)'
/

comment on column UNIFORM_WATER.TRANS_POST.CORE_DESC is 'Mô tả sau khi thực hiện hạch toán'
/

comment on column UNIFORM_WATER.TRANS_POST.CORE_DT is 'ngày thực hiện hạch toán'
/

-- TRANS_DETAIL: table
create table UNIFORM_WATER.TRANS_DETAIL
(
    ID                 VARCHAR2(50) not null
        constraint PAYMENT_TRANSACTION_DETAIL_PK
            primary key,
    TRANS_ID           VARCHAR2(100),
    ACC_NUMBER         VARCHAR2(100),
    ACC_NAME           VARCHAR2(100),
    ACC_BRN            VARCHAR2(100),
    BILL_ID            VARCHAR2(100),
    BILL_CODE          VARCHAR2(100),
    BILL_DESC          VARCHAR2(500),
    BILL_AMOUNT        NUMBER,
    SETTLED_AMOUNT     NUMBER,
    PAID_AMOUNT        NUMBER,
    DRCR_IND           VARCHAR2(1),
    TRN_DESC           VARCHAR2(500),
    PG_TRANS_ID        VARCHAR2(100),
    PG_TRANS_DESC      VARCHAR2(500),
    PG_TRANS_CODE      VARCHAR2(100),
    GN_BS_STATUS       VARCHAR2(100),
    GN_BS_DATE         DATE,
    MAKER_ID           VARCHAR2(100),
    MAKER_DT           DATE,
    AUTH_ID            VARCHAR2(100),
    AUTH_DT            DATE,
    GN_STATUS          VARCHAR2(20) not null,
    NUM_RETRY          NUMBER,
    CREATED_BY         VARCHAR2(100),
    CREATED_DATE       TIMESTAMP(6),
    LAST_MODIFIED_BY   VARCHAR2(100),
    LAST_MODIFIED_DATE TIMESTAMP(6),
    CUSTOMER_ID        VARCHAR2(300),
    CCY                VARCHAR2(20),
    BILL_TYPE          VARCHAR2(100),
    BILL_STATUS        VARCHAR2(100),
    OTHER_INFO         VARCHAR2(500),
    TRN_BRN            VARCHAR2(100),
    GN_BS_UPDATE_DATE  TIMESTAMP(6),
    COLUMN_NAME        NUMBER,
    GN_BS_UPDATE_BY    VARCHAR2(100),
    GN_BS_CREATED_DATE TIMESTAMP(6),
    GN_BS_CREATED_BY   VARCHAR2(100),
    REASON_EXTRA       VARCHAR2(500)
)
/

comment on column UNIFORM_WATER.TRANS_DETAIL.TRANS_ID is 'ID giao dịch gốc'
/

comment on column UNIFORM_WATER.TRANS_DETAIL.ACC_NUMBER is 'Số tài khoản Nhà cung cấp'
/

comment on column UNIFORM_WATER.TRANS_DETAIL.ACC_NAME is 'Tên tài khoản Nhà cung cấp'
/

comment on column UNIFORM_WATER.TRANS_DETAIL.ACC_BRN is 'CHhi nhánh tài khoản Nhà cung cấp'
/

comment on column UNIFORM_WATER.TRANS_DETAIL.BILL_ID is 'Số tháng của kì nợ'
/

comment on column UNIFORM_WATER.TRANS_DETAIL.BILL_CODE is 'Số năm của kì nợ'
/

comment on column UNIFORM_WATER.TRANS_DETAIL.BILL_DESC is 'Số danh bộ Trả về khi tra cứu nợ khách hàng'
/

comment on column UNIFORM_WATER.TRANS_DETAIL.BILL_AMOUNT is 'Nợ hoá đơn Trả về khi tra cứu nợ khách hàng '
/

comment on column UNIFORM_WATER.TRANS_DETAIL.SETTLED_AMOUNT is 'Số tiền nợ  hóa đơn'
/

comment on column UNIFORM_WATER.TRANS_DETAIL.PAID_AMOUNT is 'Số tiền đã trả phục vụ thanh toán 1 phần '
/

comment on column UNIFORM_WATER.TRANS_DETAIL.DRCR_IND is 'Tính chất nợ /có  (D or C) trường hợp này là D '
/

comment on column UNIFORM_WATER.TRANS_DETAIL.TRN_DESC is 'Mô tả , diễn giải có quy tắc generate riêng cho từng nhà cung cấp '
/

comment on column UNIFORM_WATER.TRANS_DETAIL.PG_TRANS_ID is 'TransId nhận được sau khi gọi ESB gạch nợ'
/

comment on column UNIFORM_WATER.TRANS_DETAIL.PG_TRANS_DESC is 'TransDesc nhận được sau khi gọi ESB gạch nợ'
/

comment on column UNIFORM_WATER.TRANS_DETAIL.PG_TRANS_CODE is 'TransCode nhận được sau khi gọi ESB gạch nợ'
/

comment on column UNIFORM_WATER.TRANS_DETAIL.GN_BS_STATUS is 'Trạng thái gạch nợ bổ sung (IN_PROCESS, SUCCESS, FALSE, ERROR)'
/

comment on column UNIFORM_WATER.TRANS_DETAIL.GN_BS_DATE is 'Ngày gạch nợ bổ sung'
/

comment on column UNIFORM_WATER.TRANS_DETAIL.MAKER_ID is 'ID giao dịch viên tạo'
/

comment on column UNIFORM_WATER.TRANS_DETAIL.MAKER_DT is 'Ngày tạo'
/

comment on column UNIFORM_WATER.TRANS_DETAIL.AUTH_ID is 'ID kiểm soát viên duyệt'
/

comment on column UNIFORM_WATER.TRANS_DETAIL.AUTH_DT is 'Ngày duyệt'
/

comment on column UNIFORM_WATER.TRANS_DETAIL.GN_STATUS is 'Trạng thái gạch nợ ( IN_PROCESS, SUCCESS, FALSE, ERROR, DENIED)'
/

comment on column UNIFORM_WATER.TRANS_DETAIL.NUM_RETRY is 'Số lần thử lại khi gạch nợ bổ sung'
/

comment on column UNIFORM_WATER.TRANS_DETAIL.CUSTOMER_ID is 'Mã khách hàng '
/

comment on column UNIFORM_WATER.TRANS_DETAIL.CCY is 'Loại tiền'
/

comment on column UNIFORM_WATER.TRANS_DETAIL.BILL_TYPE is 'Loại Hóa đơn  trả về khi tra cứu nợ'
/

comment on column UNIFORM_WATER.TRANS_DETAIL.BILL_STATUS is 'Trạng thái hóa đơn trả về khi tra cứu nợ'
/

comment on column UNIFORM_WATER.TRANS_DETAIL.OTHER_INFO is 'Thông tin thêm trả về khi tra cứu nợ'
/


/**
  SEQUENCE GEN ma giao dich TRANS_NO
 */
create sequence UNIFORM_WATER.TRANS_NO_SEQ
    minvalue 1000
/



