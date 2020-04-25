//
// Copyright (c) ZeroC, Inc. All rights reserved.
//
//
// Ice version 3.7.3
//
// <auto-generated>
//
// Generated from file `house.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package House;

public class FridgeDevice implements java.lang.Cloneable,
                                     java.io.Serializable
{
    public String name;

    public int temperature;

    public FridgeDevice()
    {
        this.name = "";
    }

    public FridgeDevice(String name, int temperature)
    {
        this.name = name;
        this.temperature = temperature;
    }

    public boolean equals(java.lang.Object rhs)
    {
        if(this == rhs)
        {
            return true;
        }
        FridgeDevice r = null;
        if(rhs instanceof FridgeDevice)
        {
            r = (FridgeDevice)rhs;
        }

        if(r != null)
        {
            if(this.name != r.name)
            {
                if(this.name == null || r.name == null || !this.name.equals(r.name))
                {
                    return false;
                }
            }
            if(this.temperature != r.temperature)
            {
                return false;
            }

            return true;
        }

        return false;
    }

    public int hashCode()
    {
        int h_ = 5381;
        h_ = com.zeroc.IceInternal.HashUtil.hashAdd(h_, "::House::FridgeDevice");
        h_ = com.zeroc.IceInternal.HashUtil.hashAdd(h_, name);
        h_ = com.zeroc.IceInternal.HashUtil.hashAdd(h_, temperature);
        return h_;
    }

    public FridgeDevice clone()
    {
        FridgeDevice c = null;
        try
        {
            c = (FridgeDevice)super.clone();
        }
        catch(CloneNotSupportedException ex)
        {
            assert false; // impossible
        }
        return c;
    }

    public void ice_writeMembers(com.zeroc.Ice.OutputStream ostr)
    {
        ostr.writeString(this.name);
        ostr.writeInt(this.temperature);
    }

    public void ice_readMembers(com.zeroc.Ice.InputStream istr)
    {
        this.name = istr.readString();
        this.temperature = istr.readInt();
    }

    static public void ice_write(com.zeroc.Ice.OutputStream ostr, FridgeDevice v)
    {
        if(v == null)
        {
            _nullMarshalValue.ice_writeMembers(ostr);
        }
        else
        {
            v.ice_writeMembers(ostr);
        }
    }

    static public FridgeDevice ice_read(com.zeroc.Ice.InputStream istr)
    {
        FridgeDevice v = new FridgeDevice();
        v.ice_readMembers(istr);
        return v;
    }

    static public void ice_write(com.zeroc.Ice.OutputStream ostr, int tag, java.util.Optional<FridgeDevice> v)
    {
        if(v != null && v.isPresent())
        {
            ice_write(ostr, tag, v.get());
        }
    }

    static public void ice_write(com.zeroc.Ice.OutputStream ostr, int tag, FridgeDevice v)
    {
        if(ostr.writeOptional(tag, com.zeroc.Ice.OptionalFormat.FSize))
        {
            int pos = ostr.startSize();
            ice_write(ostr, v);
            ostr.endSize(pos);
        }
    }

    static public java.util.Optional<FridgeDevice> ice_read(com.zeroc.Ice.InputStream istr, int tag)
    {
        if(istr.readOptional(tag, com.zeroc.Ice.OptionalFormat.FSize))
        {
            istr.skip(4);
            return java.util.Optional.of(FridgeDevice.ice_read(istr));
        }
        else
        {
            return java.util.Optional.empty();
        }
    }

    private static final FridgeDevice _nullMarshalValue = new FridgeDevice();

    /** @hidden */
    public static final long serialVersionUID = 1060399975L;
}
