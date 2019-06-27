package af.gov.nsia.datahub.odkservice.enumeration;

/**
 * @author Rahul Goel created on 16/6/18
 */


public enum ExceptionType {

    NoHandlerFoundException,
    Exception,
    HttpRequestMethodNotSupportedException,
    MethodArgumentNotValidException,
    MissingServletRequestParameterException,
    ConstraintViolationException,
    HttpMessageNotReadableException,
    HttpMediaTypeNotSupportedException,

    DhKafkaException,
    DhDataSourceException,
    DhResourceNotFoundException;

    private ExceptionType(){

    }
}
